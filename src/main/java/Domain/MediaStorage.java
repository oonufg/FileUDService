package Domain;
import Domain.Entities.MediaFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MediaStorage {
    private String location = "/mnt/nvme0n1p3/Ved/JavaProjects/FileUDService/src/main/resources/Media";
    public byte[] getFilePay(String fileName){
        Path pathToFile = getPathToFile(fileName);
        byte[] filePayload = {};
        try {
            filePayload = new byte[(int)Files.size(pathToFile)];
            readBytesFromFile(filePayload, pathToFile);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return filePayload;
    }

    public void saveFile(MediaFile fileToSave) {
        Path pathToFileinFileSystem = getPathToFile(fileToSave.getFullName());
        createFileIfNotExist(pathToFileinFileSystem);
        recordBytesInFile(pathToFileinFileSystem, fileToSave.getPayload());
    }

    public MediaFile getFile(String filename){
        return null;
    }
    private Path getPathToFile(String filename){
        return Path.of(location, filename);
    }

    private void createFileIfNotExist(Path intendedPathToFile){
        try {
            if (!Files.exists(intendedPathToFile)) {
                Files.createFile(intendedPathToFile);
            }
        }catch(IOException exception){
            System.out.println("Failed to create file -> " + exception.getMessage());
        }
    }

    private void recordBytesInFile(Path pathToFile, byte[] filePayload){
        try (SeekableByteChannel channel = Files.newByteChannel(pathToFile, StandardOpenOption.WRITE)) {
            channel.truncate(filePayload.length);
            ByteBuffer buffer = ByteBuffer.allocate(filePayload.length);
            for (byte b : filePayload) {
                buffer.put(b);
            }
            buffer.flip();
            channel.write(buffer);
        } catch(IOException exception){
            System.out.println("->> " + exception.getCause());
        }
    }

    private void readBytesFromFile(byte[] arrayToWrite, Path pathToFile){
        try(ByteChannel channel = Files.newByteChannel(pathToFile,StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocate(arrayToWrite.length);
            channel.read(buffer);
            for(int i = 0; i < arrayToWrite.length ; i++){
                arrayToWrite[i] = buffer.get(i);
            }
            System.out.println();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


}

