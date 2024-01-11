package com.example.iprwcbackendcode.repository;

import com.example.iprwcbackendcode.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID>{
    Category findCategoryById(UUID id);


    List<Category> findAll();
}
