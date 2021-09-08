package food.booking.app.storage.adapter.out.persistence;

import food.booking.app.storage.app.port.out.*;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import java.net.URI;
import java.time.Instant;

/**
 * Storage persistence adapter
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class StoragePersistenceAdapter implements CreateFilePort,
        LoadFileDetailsPort,
        CheckFileSlugPort,
        DeleteFilePort,
        CheckFileUrlPort {

    private final UploadedFileRepository uploadedFileRepository;

    private final StoragePersistenceMapper storagePersistenceMapper;

    private final StoragePersistenceResolver storagePersistenceResolver;

    @Override
    public File create(CreateFile createFile) {
        UploadedFileJpaEntity file = storagePersistenceMapper.mapToJpaEntity(requireValid(createFile));
        return storagePersistenceMapper.mapToDomainEntity(uploadedFileRepository.save(file));
    }

    @Override
    public void deleteBySlug(String fileSlug) {
        uploadedFileRepository.markAsDeletedBySlug(requireValidFileSlug(fileSlug), Instant.now());
    }

    @Override
    public File loadBySlug(String fileSlug) {
        UploadedFileJpaEntity file = storagePersistenceResolver.resolve(requireValidFileSlug(fileSlug));
        return storagePersistenceMapper.mapToDomainEntity(file);
    }

    @Override
    public boolean checkBySlug(String fileSlug) {
        return uploadedFileRepository.existsBySlug(requireValidFileSlug(fileSlug));
    }

    @Override
    public boolean checkUrl(URI fileUrl) {
        return uploadedFileRepository.existsByUrlAndDeletedFalse(requireValidFileUrl(fileUrl));
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
