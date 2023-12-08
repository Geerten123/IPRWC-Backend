package com.example.iprwcbackendcode.controller;

import com.example.iprwcbackendcode.dao.ProductDAO;
import com.example.iprwcbackendcode.model.ApiResponse;
import com.example.iprwcbackendcode.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse GetAllProducts(){
        return new ApiResponse<>(HttpStatus.ACCEPTED, productDAO.getAllProducts());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse GetProductById(@PathVariable UUID id){
        return new ApiResponse<>(HttpStatus.ACCEPTED, productDAO.getProductById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse PostProduct(@Valid @RequestBody Product product){
        return new ApiResponse<>(HttpStatus.ACCEPTED, productDAO.SaveProductToDatabase(product));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse UpdateProduct(@PathVariable UUID id, @Valid @RequestBody Product product){
        return new ApiResponse<>(HttpStatus.ACCEPTED, productDAO.UpdateProductInDatabase(id, product));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse DeleteProduct(@PathVariable UUID id){
        return new ApiResponse<>(HttpStatus.ACCEPTED, productDAO.DeleteProductInDatabase(id));
    }


}
