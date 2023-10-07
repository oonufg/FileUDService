package ru.pablo.Spring.Controllers;

import ru.pablo.Domain.Entities.MediaFile;
import ru.pablo.Persistence.Repositories.MediaFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class FileUDController {
    private MediaFileRepository repository;

    @Autowired
    public FileUDController(MediaFileRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<?> handleGetAllFiles(){
        return ResponseEntity.ok().body("");
    }
    @GetMapping("/{fileUID}")
    public ResponseEntity<?> handleDownloadFile(@PathVariable("fileUID") String fileUid){
        System.out.println(fileUid);
        MediaFile fileToSend = repository.getMediaFile(fileUid);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(fileToSend.getFullName())
                        .build()
        );
        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(httpHeaders)
                .body(fileToSend.getPayload());
    }

    @PostMapping()
    public ResponseEntity<?> handleUploadFile(@RequestParam("file") MultipartFile file){
            try {
                MediaFile fileToSave = new MediaFile(file.getOriginalFilename(), file.getBytes());
                repository.addMediaFile(fileToSave);
                return ResponseEntity.ok().body("");
            }catch(IOException e){
                return ResponseEntity.badRequest().body("");
            }
    }


}


