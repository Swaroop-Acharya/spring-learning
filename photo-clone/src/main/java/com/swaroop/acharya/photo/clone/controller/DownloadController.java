package com.swaroop.acharya.photo.clone.controller;

import com.swaroop.acharya.photo.clone.model.PhotoEntity;
import com.swaroop.acharya.photo.clone.service.PhotoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    private final PhotoService photoService;

    public DownloadController(PhotoService photoService){
        this.photoService=photoService;
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getOriginalPhoto(@PathVariable Integer id){
        PhotoEntity photo = photoService.getById(id);
        if(photo ==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data =photo.getData();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();

        headers.setContentDisposition(build);


        return new ResponseEntity<>(data,headers,HttpStatus.OK);
    }

}
