package Domain.Entities;

public class MediaFile {
    private long id;
    private byte[] payload;
    private String resolution;
    private String title;

    public MediaFile(String title, String resolution, byte[] payload){
        this.title = title;
        this.resolution = resolution;
        this.payload = payload;

    }

    public MediaFile(String filename, byte[] payload){
        String[] parsedFile = filename.split("\\.");
        this.title = parsedFile[0];
        this.resolution = parsedFile[1];
        this.payload = payload;
    }

    public String getResolution() {
        return resolution;
    }

    public String getTitle() {
        return title;
    }

    public String getFullName(){
        return title + "." +resolution;

    }

    public byte[] getPayload(){
        return this.payload;
    }

}
