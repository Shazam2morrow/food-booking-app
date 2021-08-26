package food.booking.app.storage.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityNotFoundException;

/**
 * Storage persistance resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class StoragePersistanceResolver {

    private final UploadedFileRepository uploadedFileRepository;

    public UploadedFileJpaEntity resolve(String fileSlug) {
        Validate.notBlank(fileSlug, "fileSlug can not be blank");
        return uploadedFileRepository.findBySlug(fileSlug)
                .orElseThrow(() -> new EntityNotFoundException("File was not found " + fileSlug));
    }

}
