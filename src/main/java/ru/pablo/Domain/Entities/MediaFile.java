package ru.pablo.Domain.Entities;

import java.util.UUID;

public class MediaFile {
    private UUID uid;
    private byte[] payload;
    private String extension;
    private String title;

    public MediaFile(String UID, String title, String extension, byte[] payload){
        this.uid = UUID.fromString(UID);
        this.title = title;
        this.extension = extension;
        this.payload = payload;

    }

    public MediaFile(String title, String extension, byte[] payload){
        this.title = title;
        this.extension = extension;
        this.payload = payload;
        this.uid = UUID.nameUUIDFromBytes(payload);
    }

    public MediaFile(String filename, byte[] payload){
        String[] parsedFile = filename.split("\\.");
        this.title = parsedFile[0];
        this.extension = parsedFile[1];
        this.payload = payload;
        this.uid = UUID.nameUUIDFromBytes(payload);
    }

    public String getExtension() {
        return extension;
    }

    public String getTitle() {
        return title;
    }

    public String getFullName(){
        return title + "." +extension;

    }

    public byte[] getPayload(){
        return this.payload;
    }


    public String getUID(){
        return uid.toString();
    }

}
