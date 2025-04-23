package com.swaroop.acharya.photo.clone.controller;

import com.swaroop.acharya.photo.clone.model.PhotoEntity;
import com.swaroop.acharya.photo.clone.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService){
        this.photoService=photoService;
    }
    @GetMapping("/")
    public String greetings(){
        return "Hello world";
    }
   @GetMapping("/photos")
    public Iterable<PhotoEntity> getAll(){
        return photoService.getAllPhotos();
   }

   @GetMapping("/photos/{id}")
    public PhotoEntity getById(@PathVariable Integer id){
        PhotoEntity photo = photoService.getById(id);
        if(photo == null) throw new  ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
   }

   @DeleteMapping("/photos/{id}")
    public void deletePhotoById(@PathVariable Integer id){
        PhotoEntity photo = photoService.getById(id);
        if(photo ==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        photoService.removeById(id);
   }
   @PostMapping("/photos")
    public PhotoEntity createNewPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.save(file.getOriginalFilename(),file.getContentType(),file.getBytes());
   }


}
