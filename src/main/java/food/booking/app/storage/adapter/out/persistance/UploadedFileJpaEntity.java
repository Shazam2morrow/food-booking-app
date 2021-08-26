package food.booking.app.storage.adapter.out.persistance;

import food.booking.app.shared.persistance.UriPersistanceConverter;
import food.booking.app.shared.size.ChecksumSize;
import food.booking.app.shared.size.SlugSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;
import java.time.Instant;

/**
 * Uploaded file JPA entity
 *
 * @author shazam2morrow
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "uploaded_file", uniqueConstraints = {
        @UniqueConstraint(name = "uploaded_file_url_uk", columnNames = {"url"}),
        @UniqueConstraint(name = "uploaded_file_slug_uk", columnNames = {"slug"})
})
class UploadedFileJpaEntity implements Serializable {

    /**
     * Primary identifier
     * <p>
     * Example: 1
     */
    @Id
    @NotNull(message = "id can not be null")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uploaded_file_seq_gen")
    @SequenceGenerator(name = "uploaded_file_seq_gen", sequenceName = "uploaded_file_seq", allocationSize = 1)
    private Long id;

    /**
     * Size
     * <p>
     * Size of the uploaded file in bytes
     * <p>
     * Example: 1024
     */
    @Getter
    @Column(name = "size", nullable = false, updatable = false)
    private Long size;

    /**
     * Storage
     * <p>
     * Storage used for storing the uploaded file
     * <p>
     * Example: {@link StorageType#LOCAL}
     */
    @Getter
    @Column(name = "storage", nullable = false, updatable = false)
    @Type(type = "food.booking.app.storage.adapter.out.persistance.StorageUserType")
    private StorageType storage;

    /**
     * Slug
     * <p>
     * Randomly generated unique identifier for the uploaded file
     * <p>
     * Example: JXhdc7pESkHAh65TDLm9tDkV4Q9azr7E
     */
    @Getter
    @Column(name = "slug", unique = true, nullable = false, updatable = false, length = SlugSize.MAX)
    private String slug;

    /**
     * Checksum
     * <p>
     * SHA1 message digest of the uploaded file
     * <p>
     * Example: 2fd4e1c67a2d28fced849ee1bb76e7391b93eb12
     */
    @Getter
    @Column(name = "checksum", nullable = false, updatable = false, length = ChecksumSize.MAX)
    private String checksum;

    /**
     * Mime type
     * <p>
     * Mime type of the uploaded file
     * <p>
     * Example: image/png
     */
    @Getter
    @Column(name = "mime_type", nullable = false, updatable = false)
    private String mimeType;

    /**
     * Original name
     * <p>
     * Original name of the uploaded file provided by a client
     * <p>
     * Example: my_file.txt
     */
    @Getter
    @Column(name = "original_name", nullable = false, updatable = false)
    private String originalName;

    /**
     * URI
     * <p>
     * URI to the uploaded file
     * <p>
     * Example: https://example.com/storage/-f1GqM0xR2yj3l_X6Z.tW9dDIH2Dzw1B
     */
    @Getter
    @Convert(converter = UriPersistanceConverter.class)
    @Column(name = "url", unique = true, nullable = false, updatable = false)
    private URI url;

    /**
     * Status
     * <p>
     * Is uploaded file deleted?
     * <p>
     * Example: true
     * <p>
     * Default: false
     */
    @Getter
    @Setter
    @Column(name = "is_deleted", nullable = false, updatable = false)
    private Boolean deleted;

    /**
     * Upload timestamp
     * <p>
     * When file was uploaded to the server
     * <p>
     * Example: 2021-08-03 18:54:06.056721Z
     * <p>
     * Default: now
     */
    @Getter
    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private Instant uploadedAt;

    /**
     * Delition timestamp
     * <p>
     * When file was deleted from the server
     * <p>
     * Example: 2021-08-03 18:54:06.056721Z
     * <p>
     * Default: null
     */
    @Getter
    @Setter
    @Nullable
    @Column(name = "deleted_at")
    private Instant deletedAt;

    UploadedFileJpaEntity(URI url,
                          Long size,
                          String slug,
                          String mimeType,
                          String checksum,
                          StorageType storage,
                          String originalName) {
        this.url = url;
        this.size = size;
        this.slug = slug;
        this.storage = storage;
        this.mimeType = mimeType;
        this.checksum = checksum;
        this.deleted = Boolean.FALSE;
        this.uploadedAt = Instant.now();
        this.originalName = originalName;
    }

    Boolean isDeleted() {
        return deleted;
    }

}
