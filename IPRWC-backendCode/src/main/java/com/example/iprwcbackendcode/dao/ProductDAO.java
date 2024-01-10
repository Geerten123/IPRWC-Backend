package com.example.iprwcbackendcode.dao;

import com.example.iprwcbackendcode.model.Product;
import com.example.iprwcbackendcode.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(UUID id){
        return productRepository.findProductById(id);
    }


    public Product SaveProductToDatabase(Product product) {
        return productRepository.save(product);
    }

    public Product UpdateProductInDatabase(UUID id, Product product) {
        Product oldProduct = productRepository.findProductById(id);

        if(product.getName() != null){
            oldProduct.setName(product.getName());
        }
        if(product.getPrice() != 0){;
            oldProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null){
            oldProduct.setDescription(product.getDescription());
        }
        if(product.getCategory() != null){
            oldProduct.setCategory(product.getCategory());
        }

        return productRepository.save(oldProduct);
    }

    public Product DeleteProductInDatabase(UUID id) {
        Product product = productRepository.findProductById(id);
        productRepository.delete(product);
        return product;
    }

    public List<Product> getProductsByCategory(UUID id){
        return productRepository.findProductsByCategory(id);
    }
}
