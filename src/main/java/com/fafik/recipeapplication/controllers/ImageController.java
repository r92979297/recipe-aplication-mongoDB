package com.fafik.recipeapplication.controllers;

import com.fafik.recipeapplication.command.RecipeCommand;
import com.fafik.recipeapplication.services.ImageService;
import com.fafik.recipeapplication.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id , Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id).block());

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile")MultipartFile file){
        imageService.saveImageFile(id,file).block();
        return "redirect:/recipe/"+id+"/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void  renderImageFromDB(@PathVariable String id , HttpServletResponse response) throws IOException{
        RecipeCommand command = recipeService.findCommandById(id).block();

        if(command.getImage()==null || command.getImage().length==0)
            log.error("Image is null");
        else{
            log.debug("iamge byte sizes:" +command.getImage().length);
        }
        byte[] bytArray = new byte[command.getImage().length];

        int i =0;
        for(Byte wrappedByte : command.getImage()){
            bytArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(bytArray);
        IOUtils.copy(is,response.getOutputStream());
    }

}
