package com.fafik.recipeapplication.repositories.reactive;

import com.fafik.recipeapplication.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    public static final String TEXMEX = "Texmex";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp()throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() throws Exception{

        Category category = new Category();
        category.setDescription(TEXMEX);
        categoryReactiveRepository.save(category).block();
        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);
    }
    @Test
    public void testCategoryFindByDescription() throws Exception{

        Category category = new Category();
        category.setDescription(TEXMEX);

        categoryReactiveRepository.save(category).block();

       Category fetchedCategory = categoryReactiveRepository.findByDescription(TEXMEX).block();

        assertEquals(TEXMEX,fetchedCategory.getDescription());
    }
}