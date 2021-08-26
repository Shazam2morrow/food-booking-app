package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.DeleteFileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * File delete controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
class DeleteFileController {

    private final DeleteFileUseCase deleteFileUseCase;

    /**
     * Delete file
     *
     * @param fileSlug file slug
     * @return no content
     */
    @DeleteMapping(path = "/{fileSlug}")
    ResponseEntity<Void> deleteFile(@PathVariable String fileSlug) {
        log.debug("REST request to delete file: {}", fileSlug);
        deleteFileUseCase.deleteBySlug(fileSlug);
        return ResponseEntity.noContent().build();
    }

}
