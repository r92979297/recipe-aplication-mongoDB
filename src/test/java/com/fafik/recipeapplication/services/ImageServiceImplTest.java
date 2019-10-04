package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeReactiveRepository);
    }

    @Test
    public void saveImageFile() throws Exception{

        String id = "1";
        MultipartFile multipartFile= new MockMultipartFile("recipefile","testing.txt","text/plain","Fafik here !!".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);


        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));
        ArgumentCaptor<Recipe> argumentCaptor=ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(id,multipartFile);

        verify(recipeReactiveRepository,times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length,savedRecipe.getImage().length);
    }

}