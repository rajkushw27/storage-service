package com.rajkushw.storageservice.service;


import com.rajkushw.storageservice.entity.ImageData;
import com.rajkushw.storageservice.repository.StorageRepo;
import com.rajkushw.storageservice.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepo storageRepo;

    public String uploadImage(MultipartFile fileName) throws IOException {

        ImageData imageData = storageRepo.save(ImageData.builder()
                .name(fileName.getOriginalFilename())
                .type(fileName.getContentType())
                .imageData(ImageUtil.compressImage(fileName.getBytes())).build());

        if(imageData!=null) return "file uploaded successfully " + fileName.getName();
        else return null;

    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageRepo.findByName(fileName);
        byte[] imageData = ImageUtil.decompressImage(dbImageData.get().getImageData());
        return imageData;
    }


}
