package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, UUID> {

    ShoppingCart[] findAllByUserId(UUID id);

    ShoppingCart deleteByUserIdAndProductId(UUID userId, UUID productId);


    ShoppingCart[] findAllByUserIdAndProductId(UUID userId, UUID productId);

//    ShoppingCart findByUserIdAndProductId(UUID userId, UUID productId);

    void deleteById(UUID id);

}
