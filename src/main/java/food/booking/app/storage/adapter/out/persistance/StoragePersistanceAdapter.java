package food.booking.app.storage.adapter.out.persistance;

import food.booking.app.storage.app.port.out.*;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.net.URI;
import java.time.Instant;

/**
 * Storage persistance adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class StoragePersistanceAdapter implements CreateFilePort,
        LoadFileDetailsPort,
        CheckFileSlugPort,
        DeleteFilePort,
        CheckFileUrlPort {

    private final UploadedFileRepository uploadedFileRepository;

    private final StoragePersistanceMapper storagePersistanceMapper;

    private final StoragePersistanceResolver storagePersistanceResolver;

    @Override
    public File create(CreateFile createFile) {
        UploadedFileJpaEntity file = storagePersistanceMapper.mapToJpaEntity(requireValid(createFile));
        return storagePersistanceMapper.mapToDomainEntity(uploadedFileRepository.save(file));
    }

    @Override
    public void deleteBySlug(String fileSlug) {
        uploadedFileRepository.markAsDeletedBySlug(requireValidFileSlug(fileSlug), Instant.now());
    }

    @Override
    public File loadBySlug(String fileSlug) {
        UploadedFileJpaEntity file = storagePersistanceResolver.resolve(requireValidFileSlug(fileSlug));
        return storagePersistanceMapper.mapToDomainEntity(file);
    }

    @Override
    public boolean checkBySlug(String fileSlug) {
        return uploadedFileRepository.existsBySlug(requireValidFileSlug(fileSlug));
    }

    @Override
    public boolean checkUrl(URI fileUrl) {
        return uploadedFileRepository.existsByUrl(requireValidFileUrl(fileUrl));
    }

    /**
     * Require valid object
     *
     * @param createFile create uploaded file
     * @return validated object
     */
    private CreateFile requireValid(CreateFile createFile) {
        return Validate.notNull(createFile, "createUploadedFile can not be null");
    }

    /**
     * Validate file slug
     *
     * @param fileSlug file slug
     * @return validated file slug
     */
    private String requireValidFileSlug(String fileSlug) {
        return Validate.notBlank(fileSlug, "fileSlug can not be blank");
    }

    /**
     * Validate file URL
     *
     * @param fileUrl file URL
     * @return validate file URL
     */
    private URI requireValidFileUrl(URI fileUrl) {
        return Validate.notNull(fileUrl, "fileUrl can not be null");
    }

}
