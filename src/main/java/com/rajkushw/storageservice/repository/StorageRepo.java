package com.rajkushw.storageservice.repository;

import com.rajkushw.storageservice.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepo extends JpaRepository <ImageData,Long> {

    Optional <ImageData> findByName(String fileName);
}
