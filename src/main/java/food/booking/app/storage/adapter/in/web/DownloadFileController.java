package food.booking.app.storage.adapter.in.web;

import food.booking.app.storage.app.port.in.LoadFileDetailsUseCase;
import food.booking.app.storage.domain.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * File download controller
 *
 * @author shazam2morrow
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
class DownloadFileController {

    private final LoadFileDetailsUseCase loadFileDetailsUseCase;

    /**
     * Download file
     *
     * @param fileSlug file slug
     * @param response http servlet response
     * @return streaming response body
     * @throws IOException if file can not be downloaded
     */
    @GetMapping("/{fileSlug}")
    ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable String fileSlug,
                                                       HttpServletResponse response) throws IOException {
        log.debug("REST request to download file: {}", fileSlug);
        File file = loadFileDetailsUseCase.loadBySlug(fileSlug);
        if (file.isDeleted()) {
            throw new food.booking.app.storage.app.port.in.exception.FileNotFoundException(file.getSlug());
        } else {
            response.setContentType(file.getMimeType());
            response.setContentLengthLong(file.getSize());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, buildContentDisposition(file.getOriginalName()));
            InputStream stream = buildInputStream(file.getAbsolutePath());
            return ResponseEntity.ok(buildResponseBody(stream, response.getOutputStream()));
        }
    }

    /**
     * Build content disposition
     *
     * @param fileName file name
     * @return content disposition
     */
    private String buildContentDisposition(String fileName) {
        return String.format("attachment;filename=\"%s\"", fileName);
    }

    /**
     * Build input stream
     *
     * @param absolutePath absolute path
     * @return input stream
     * @throws FileNotFoundException thrown if file was not found
     */
    private InputStream buildInputStream(String absolutePath) throws FileNotFoundException {
        return new FileInputStream(absolutePath);
    }

    /**
     * Build response body
     *
     * @param inputStream  input stream
     * @param outputStream output stream
     * @return streaming response body
     */
    private StreamingResponseBody buildResponseBody(InputStream inputStream, OutputStream outputStream) {
        return srb -> {
            int nRead;
            byte[] buffer = new byte[8192];
            try (InputStream is = inputStream; OutputStream os = outputStream) {
                while ((nRead = is.read(buffer, 0, buffer.length)) != -1) {
                    os.write(buffer, 0, nRead);
                }
                os.flush();
            }
        };
    }

}
