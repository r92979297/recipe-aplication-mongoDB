package com.fafik.recipeapplication.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(String id, MultipartFile file);
}
