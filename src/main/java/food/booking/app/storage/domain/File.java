package food.booking.app.storage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.Instant;

/**
 * File
 *
 * @author shazam2morrow
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File {

    private URI url;
    private Long size;
    private String slug;
    private String mimeType;
    private String checksum;
    private Boolean deleted;
    private Instant deletedAt;
    private Instant uploadedAt;
    private String originalName;

    @JsonIgnore
    private String absolutePath;

    public Boolean isDeleted() {
        return deleted;
    }

}
