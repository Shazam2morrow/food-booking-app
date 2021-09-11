package food.booking.app.storage.app;

import food.booking.app.shared.size.SlugSize;
import food.booking.app.storage.adapter.out.persistence.StorageType;
import food.booking.app.storage.app.port.in.*;
import food.booking.app.storage.app.port.in.exception.InvalidFileNameException;
import food.booking.app.storage.app.port.in.exception.InvalidFileNameException.InvalidFileNameConstraintViolation;
import food.booking.app.storage.app.port.in.exception.StorageException;
import food.booking.app.storage.app.port.out.*;
import food.booking.app.storage.domain.File;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;

/**
 * Local storage service
 *
 * @author shazam2morrow
 */
@Slf4j
class LocalStorageService implements UploadFileUseCase,
        LoadFileDetailsUseCase,
        GenerateFileSlugUseCase,
        DeleteFileUseCase {

    private final static String DEFAULT_MIME_TYPE = "application/octet-stream";

    private final static String STORAGE_LOCATION_ERROR_MSG = "Could not create the directory %s where the uploaded files will be stored!";

    private final static String MIME_GUESS_WARN_MSG = "Failed to guess mime type of a file. Returning '" + DEFAULT_MIME_TYPE + "' as default value!";

    private final Path fileStorageLocation;

    private final CreateFilePort createFilePort;

    private final DeleteFilePort deleteFilePort;

    private final CheckFileSlugPort checkFileSlugPort;

    private final LoadFileDetailsPort loadFileDetailsPort;

    private final RandomStringGenerator randomStringGenerator;

    LocalStorageService(
            CreateFilePort createFilePort,
            DeleteFilePort deleteFilePort,
            StorageProperties storageProperties,
            CheckFileSlugPort checkFileSlugPort,
            LoadFileDetailsPort loadFileDetailsPort,
            RandomStringGenerator randomStringGenerator) {
        String uploadDirectory = storageProperties.getUploadDirectory();
        fileStorageLocation = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException ex) {
            throw new IllegalStateException(String.format(STORAGE_LOCATION_ERROR_MSG, uploadDirectory));
        }
        this.createFilePort = createFilePort;
        this.deleteFilePort = deleteFilePort;
        this.checkFileSlugPort = checkFileSlugPort;
        this.loadFileDetailsPort = loadFileDetailsPort;
        this.randomStringGenerator = randomStringGenerator;
    }

    @Override
    @Transactional
    public File upload(UploadFileCommand command) {
        Validate.notNull(command, "command can not be null");
        log.debug("Trying to upload file: {}", command);
        try {
            Resource resource = command.getResource();
            // get original file name and remove any absolute paths
            String originalName = FilenameUtils.getName(resource.getFilename());
            // check original name for invalid path sequences
            if (originalName.contains("..")) {
                throw new InvalidFileNameException(
                        "File name contains illegal characters!",
                        new HashSet<>(List.of(new InvalidFileNameConstraintViolation(resource))));
            }
            String slug = command.getSlug();
            // resolve target location on the local file system for the file
            Path targetLocation = fileStorageLocation.resolve(slug);
            // try to store file
            try (InputStream is = resource.getInputStream()) {
                // copy file to the target location replacing any existing ones with the same name
                Files.copy(is, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                // get absolute path and content length of a file
                String absolutePath = targetLocation.toAbsolutePath().toString();
                long contentLength = resource.contentLength();
                log.debug("Copied uploaded file that has size of {} bytes to {}.", contentLength, absolutePath);
                // create uploaded file
                var createUploadedFile = new CreateFile(
                        command.getUrl(),
                        contentLength,
                        slug,
                        guessMimeType(targetLocation.toFile()),
                        calculateChecksum(targetLocation),
                        originalName,
                        StorageType.LOCAL
                );
                // save information about file in persistent storage
                return createFilePort.create(createUploadedFile);
            }
        } catch (IOException ex) {
            throw new StorageException("Failed to upload file!", ex);
        }
    }

    @Override
    @Transactional
    public void deleteBySlug(String fileSlug) {
        File file = loadFileDetailsPort.loadBySlug(requireValidSlug(fileSlug));
        if (!file.isDeleted() && fileStorageLocation.resolve(file.getSlug()).toFile().delete()) {
            deleteFilePort.deleteBySlug(file.getSlug());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public File loadBySlug(String fileSlug) {
        File file = loadFileDetailsPort.loadBySlug(requireValidSlug(fileSlug));
        String absolutePath = fileStorageLocation.resolve(file.getSlug()).toAbsolutePath().toString();
        file.setAbsolutePath(absolutePath);
        return file;
    }

    @Override
    @Transactional(readOnly = true)
    public String generateSlug() {
        String slug;
        do {
            slug = randomStringGenerator.generate(SlugSize.MAX);
        } while (checkFileSlugPort.checkBySlug(slug));
        return slug;
    }

    /**
     * Calculate checksum
     *
     * @param path path
     * @return checksum
     * @throws IOException if checksum can not be calculated
     */
    private String calculateChecksum(Path path) throws IOException {
        try (var stream = new FileInputStream(path.toFile())) {
            return DigestUtils.sha1Hex(stream);
        }
    }

    /**
     * Guess mime type of a file
     * <p>
     * Returns guessed mime type of the file or 'application/octet-stream' if failed to guess
     *
     * @param file file
     * @return mime type of a file
     */
    private String guessMimeType(java.io.File file) {
        try {
            return Magic.getMagicMatch(file, false).getMimeType();
        } catch (MagicParseException | MagicMatchNotFoundException | MagicException ex) {
            log.warn(MIME_GUESS_WARN_MSG, ex);
            return DEFAULT_MIME_TYPE;
        }
    }

    /**
     * Validate file slug
     *
     * @param fileSlug file slug
     * @return validated file slug
     */
    private String requireValidSlug(String fileSlug) {
        return Validate.notBlank(fileSlug, "fileSlug can not be blank");
    }

}
