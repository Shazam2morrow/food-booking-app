package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.LoadFileDetailsUseCase;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Load file details controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
class LoadFileDetailsController {

    private final LoadFileDetailsUseCase loadFileDetailsUseCase;

    /**
     * Load file details
     *
     * @param fileSlug file slug
     * @return file details
     */
    @GetMapping(path = "/{fileSlug}/info")
    ResponseEntity<File> loadFileDetails(@PathVariable String fileSlug) {
        log.debug("REST request to load file details: {}", fileSlug);
        return ResponseEntity.ok(loadFileDetailsUseCase.loadBySlug(fileSlug));
    }

}
