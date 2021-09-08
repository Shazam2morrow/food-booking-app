package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.DeleteFileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
     */
    @DeleteMapping(path = "/{fileSlug}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteFile(@PathVariable String fileSlug) {
        log.debug("REST request to delete file: {}", fileSlug);
        deleteFileUseCase.deleteBySlug(fileSlug);
    }

}
