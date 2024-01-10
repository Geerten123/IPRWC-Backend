package com.example.iprwcbackendcode.controller;

import com.example.iprwcbackendcode.dao.ShoppingCartDAO;
import com.example.iprwcbackendcode.model.ApiResponse;
import com.example.iprwcbackendcode.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse GetAllProductsForUser(){
        try {
            UUID userId = userService.getUserFromAuth().getId();
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.shoppingCartDAO.GetAllProductsForUser(userId));
        } catch (NullPointerException e){
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, e);
        }
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.POST)
    public ApiResponse AddProductToCart(@PathVariable UUID productId, @Valid @RequestBody int amount){
        UUID userId = userService.getUserFromAuth().getId();
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.shoppingCartDAO.AddProductToCart(userId, productId, amount));
    }

    @RequestMapping(value = "/{productId}",method = RequestMethod.DELETE)
    public ApiResponse RemoveProductFromCart(@PathVariable UUID productId){
        UUID userId = userService.getUserFromAuth().getId();
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.shoppingCartDAO.DeleteProductFromCart(userId, productId));
    }

    @RequestMapping(value = "/{productId}",method = RequestMethod.PUT)
    public ApiResponse UpdateProductInCart(@PathVariable UUID productId, @Valid @RequestBody int amount){
        UUID userId = userService.getUserFromAuth().getId();
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.shoppingCartDAO.UpdateProductInCart(userId, productId, amount));
    }
}
