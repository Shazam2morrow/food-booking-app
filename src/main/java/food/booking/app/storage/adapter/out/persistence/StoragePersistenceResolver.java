package food.booking.app.storage.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Storage persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class StoragePersistenceResolver {

    private final UploadedFileRepository uploadedFileRepository;

    public UploadedFileJpaEntity resolve(String fileSlug) {
        Validate.notBlank(fileSlug, "fileSlug can not be blank");
        return uploadedFileRepository.findBySlug(fileSlug)
                .orElseThrow(() -> new EntityNotFoundException("File was not found " + fileSlug));
    }

}
