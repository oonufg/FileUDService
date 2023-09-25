package Spring.Controllers;

import Domain.Entities.MediaFile;
import Domain.MediaStorage;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/")
public class FileUDController {
    MediaStorage storage = new MediaStorage();
    public ResponseEntity<?> handleGetAllFiles(){
        return ResponseEntity.ok().body("");
    }
    @GetMapping()
    public ResponseEntity<?> handleDownloadFile(){
        byte[] payload = storage.getFilePay("ok.txt");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(
                ContentDisposition.attachment()
                        .filename("cock.txt")
                        .build()
        );
        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(httpHeaders)
                .body(payload);
    }

    @PostMapping
    public ResponseEntity<?> handleUploadFile(InputStream stream){
        try {
            byte[] pl = stream.readAllBytes();
            MediaFile fileToSave = new MediaFile("ok","txt", pl);
            storage.saveFile(fileToSave);
            return ResponseEntity.ok().body("");
        }catch(IOException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("");
        }
    }


}


