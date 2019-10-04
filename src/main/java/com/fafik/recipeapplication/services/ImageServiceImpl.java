package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    public final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }


    @Override
    public Mono<Void>  saveImageFile(String id, MultipartFile file) {
       Mono<Recipe> recipeMono=recipeReactiveRepository
                .findById(id)
                .map(recipe->{
                    Byte[] byteObjects = new Byte[0];
                    try{
                       byteObjects= new Byte[file.getBytes().length];

                        int i =0;

                        for(byte b : file.getBytes()){
                            byteObjects[i++]=b;
                        }

                        recipe.setImage(byteObjects);

                        return recipe;
                    }catch(IOException e){
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
       recipeReactiveRepository.save(recipeMono.block()).block();




        return Mono.empty();
    }
}
