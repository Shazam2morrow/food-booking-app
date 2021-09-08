package food.booking.app.storage.adapter.out.persistence;

import food.booking.app.storage.app.port.in.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;

/**
 * Storage persistence resolver
 *
 * @author shazam2morrow
 */
@RequiredArgsConstructor
class StoragePersistenceResolver {

    private final UploadedFileRepository uploadedFileRepository;

    /**
     * Resolve file
     *
     * @param fileSlug file slug
     * @return resolved file
     * @throws FileNotFoundException if file was not found
     */
    public UploadedFileJpaEntity resolve(String fileSlug) {
        return uploadedFileRepository.findBySlug(requireValidSlug(fileSlug))
                .orElseThrow(() -> new FileNotFoundException(fileSlug));
    }

    /**
     * Validate slug
     *
     * @param fileSlug file slug
     * @return valid file slug
     */
    private String requireValidSlug(String fileSlug) {
        return Validate.notBlank(fileSlug, "fileSlug can not be blank");
    }

}
