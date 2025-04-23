package com.swaroop.acharya.photo.clone.service;

import com.swaroop.acharya.photo.clone.model.PhotoEntity;
import com.swaroop.acharya.photo.clone.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    
    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository=photoRepository;
    }

    public Iterable<PhotoEntity> getAllPhotos(){
       return photoRepository.findAll();
    }
    public PhotoEntity getById(Integer id){
        return photoRepository.findById(id).orElse(null);
    }

    public void removeById(Integer id){
        photoRepository.deleteById(id);
    }

    public PhotoEntity save(String fileName,String contentType,byte[] data){
        PhotoEntity photo = new PhotoEntity();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }

}
