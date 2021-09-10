package food.booking.app.storage.adapter.in.web;

import food.booking.app.shared.util.UriBuilderUtil;
import food.booking.app.storage.app.port.out.FileDownloadUriBuilderPort;
import org.apache.commons.lang3.Validate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Local file download URI builder
 *
 * @author shazam2morrow
 */
class LocalFileDownloadUriBuilder implements FileDownloadUriBuilderPort {

    private final static Class<DownloadFileController> CLAZZ = DownloadFileController.class;

    /**
     * Build file download URI
     *
     * @param fileSlug file slug
     * @return file download URI
     * @see DownloadFileController#downloadFile(String, HttpServletResponse)
     */
    public URI buildUri(String fileSlug) {
        return UriBuilderUtil.buildUri(CLAZZ, "downloadFile", requireValid(fileSlug), new Object());
    }

    /**
     * Validate file slug
     *
     * @param fileSlug file slug
     * @return validated file slug
     */
    private String requireValid(String fileSlug) {
        return Validate.notBlank(fileSlug, "fileSlug can not be blank");
    }

}
