package food.booking.app.storage.adapter.out.persistance;

import food.booking.app.storage.app.port.out.CreateFile;
import food.booking.app.storage.domain.File;

/**
 * Storage persistance mapper
 *
 * @author shazam2morrow
 */
class StoragePersistanceMapper {

    UploadedFileJpaEntity mapToJpaEntity(CreateFile createFile) {
        return new UploadedFileJpaEntity(
                createFile.url(),
                createFile.size(),
                createFile.slug(),
                createFile.mimeType(),
                createFile.checksum(),
                createFile.storage(),
                createFile.originalName());
    }

    File mapToDomainEntity(UploadedFileJpaEntity entity) {
        File file = new File();
        file.setUrl(entity.getUrl());
        file.setSize(entity.getSize());
        file.setSlug(entity.getSlug());
        file.setDeleted(entity.isDeleted());
        file.setChecksum(entity.getChecksum());
        file.setMimeType(entity.getMimeType());
        file.setChecksum(entity.getChecksum());
        file.setDeletedAt(entity.getDeletedAt());
        file.setUploadedAt(entity.getUploadedAt());
        file.setOriginalName(entity.getOriginalName());
        return file;
    }

}
