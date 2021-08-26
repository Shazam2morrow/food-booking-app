package food.booking.app.shared;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * URI builder utility
 *
 * @author shazam2morrow
 */
public class UriBuilderUtil {

    /**
     * Build URI
     *
     * @param clazz      target class
     * @param methodName method name
     * @param args       arguments
     * @return URI
     */
    public static URI buildUri(Class<?> clazz, String methodName, Object... args) {
        return MvcUriComponentsBuilder.fromMethodName(clazz, methodName, args)
                .build()
                .encode()
                .toUri();
    }

    /**
     * Build href
     *
     * @param builder builder
     * @param page    page
     * @return paged href
     */
    public static String buildPagedHref(UriComponentsBuilder builder, Pageable page) {
        return builder
                .queryParam("page", page.getPageNumber())
                .queryParam("size", page.getPageSize())
                .encode()
                .toUriString();
    }

}
