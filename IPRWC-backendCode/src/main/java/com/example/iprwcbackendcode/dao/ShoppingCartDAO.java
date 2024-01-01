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
        ShoppingCart[] shoppingCarts = this.shoppingCartRepository.findAllByUserId(userId);

        for (ShoppingCart shoppingCart : shoppingCarts) {
            System.out.println("test");
            System.out.println(shoppingCart.getProductId());
        }

        return shoppingCarts;
    }

    public ShoppingCart AddProductToCart(UUID userId, UUID productId, int amount){
        ShoppingCart[] cart = this.shoppingCartRepository.findAllByUserIdAndProductId(userId, productId);
        int totalAmount = amount;
        for (ShoppingCart cartItem : cart) {
            System.out.println(cartItem.getId());
            totalAmount += cartItem.getQuantity();
            this.shoppingCartRepository.deleteById(cartItem.getId());
        }

        ShoppingCart shoppingCart = new ShoppingCart(userId, productId, totalAmount);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public String DeleteProductFromCart(UUID userId, UUID productId){
        System.out.println(userId);
        System.out.println(productId);
        ShoppingCart[] cart = this.shoppingCartRepository.findAllByUserIdAndProductId(userId, productId);
        for (ShoppingCart cartItem : cart) {
            this.shoppingCartRepository.deleteById(cartItem.getId());
        }
        return "Product deleted from cart";
    }

//    public ShoppingCart UpdateProductInCart(UUID userId, UUID productId, int amount){
//        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserIdAndProductId(userId, productId);
//        shoppingCart.setQuantity(amount);
//        return this.shoppingCartRepository.save(shoppingCart);
//    }
}
