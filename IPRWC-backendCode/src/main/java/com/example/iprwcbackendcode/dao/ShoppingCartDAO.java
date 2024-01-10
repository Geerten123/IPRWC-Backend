package com.example.iprwcbackendcode.dao;

import com.example.iprwcbackendcode.model.ShoppingCart;
import com.example.iprwcbackendcode.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShoppingCartDAO {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    public ShoppingCart[] GetAllProductsForUser(UUID userId){
        return this.shoppingCartRepository.findAllByUserId(userId);
    }

    public ShoppingCart AddProductToCart(UUID userId, UUID productId, int amount){
        ShoppingCart[] cart = this.shoppingCartRepository.findAllByUserIdAndProductId(userId, productId);
        for (ShoppingCart cartItem : cart) {
            this.shoppingCartRepository.deleteById(cartItem.getId());
        }

        if (amount <= 0){
            return null;
        }
        ShoppingCart shoppingCart = new ShoppingCart(userId, productId, amount);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart DeleteProductFromCart(UUID userId, UUID productId){
        ShoppingCart[] cart = this.shoppingCartRepository.findAllByUserIdAndProductId(userId, productId);
        ShoppingCart lastitem = null;
        for (ShoppingCart cartItem : cart) {
            this.shoppingCartRepository.deleteById(cartItem.getId());
            lastitem = cartItem;
        }
        return lastitem;
    }

    public ShoppingCart UpdateProductInCart(UUID userId, UUID productId, int amount){
        ShoppingCart[] shoppingCart = this.shoppingCartRepository.findAllByUserIdAndProductId(userId, productId);
        for (ShoppingCart cartItem : shoppingCart) {
            this.shoppingCartRepository.deleteById(cartItem.getId());
        }
        ShoppingCart cartItem = new ShoppingCart(userId, productId, amount);
        return this.shoppingCartRepository.save(cartItem);
    }
}
