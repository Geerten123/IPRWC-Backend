package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {


    List<Product> findAll();

//    Product save(Product product, UUID id);

    Product findProductById(UUID id);

    List<Product> findProductsByCategory(UUID id);

}
