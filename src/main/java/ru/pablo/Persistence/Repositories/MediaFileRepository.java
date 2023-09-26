package ru.pablo.Persistence.Repositories;

import ru.pablo.Domain.Entities.MediaFile;
import ru.pablo.Domain.MediaStorage;
import ru.pablo.Persistence.Tables.FilesTable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MediaFileRepository {
    private static FilesTable filesTable;
    private static MediaStorage mediaStorage;

    static{
        filesTable = new FilesTable();
        mediaStorage = new MediaStorage();
    }

    public void addMediaFile(MediaFile file){
        filesTable.saveFileInfo(file.getUID(), file.getTitle(), file.getExtension());
        mediaStorage.saveFile(file.getUID(), file.getPayload());
    }

    public MediaFile getMediaFile(String uid){
        Map<String, Object> fileInfo = filesTable.getFileInfoByUID(uid);
        MediaFile resultFile = new MediaFile(
                (String)fileInfo.get("uid"),
                (String)fileInfo.get("title"),
                (String)fileInfo.get("extension"),
                mediaStorage.getFilePay((String)fileInfo.get("uid"))
        );
        return resultFile;
    }

}
