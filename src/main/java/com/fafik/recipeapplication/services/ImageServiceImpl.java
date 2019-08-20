package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    public final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImageFile(String id, MultipartFile file) {
        try{
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] byteObjects= new Byte[file.getBytes().length];

            int i =0;

            for(byte b : file.getBytes()){
                byteObjects[i++]=b;
            }

            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        }catch(IOException e){
            log.error("Error occured:"+e);
            e.printStackTrace();
        }
    }
}
