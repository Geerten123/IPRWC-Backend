package com.example.iprwcbackendcode.controller;

import com.example.iprwcbackendcode.dao.CategoryDAO;
import com.example.iprwcbackendcode.model.ApiResponse;
import com.example.iprwcbackendcode.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse GetAllCategories(){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.categoryDAO.getAllCategories());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse GetCategoryById(@PathVariable UUID id){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.categoryDAO.getCategoryById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse AddCategory(@RequestBody Category name){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.categoryDAO.AddCategoryToDatabase(name));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse DeleteCategory(@PathVariable UUID id){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.categoryDAO.DeleteCategoryInDatabase(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse UpdateCategory(@PathVariable UUID id, @RequestBody Category category){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.categoryDAO.UpdateCategoryInDatabase(id, category));
    }
}
