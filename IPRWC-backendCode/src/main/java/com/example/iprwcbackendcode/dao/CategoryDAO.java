package com.example.iprwcbackendcode.dao;

import com.example.iprwcbackendcode.model.Category;
import com.example.iprwcbackendcode.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CategoryDAO {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id){
        return categoryRepository.findCategoryById(id);
    }

    public Category AddCategoryToDatabase(Category category) {
        Category newCategory = new Category(category.getName());
        return categoryRepository.save(newCategory);
    }

    public Category DeleteCategoryInDatabase(UUID id) {
        Category category = categoryRepository.findCategoryById(id);
        categoryRepository.delete(category);
        return category;
    }

    public Category UpdateCategoryInDatabase(UUID id, Category category) {
        Category oldCategory = categoryRepository.findCategoryById(id);
        oldCategory.setName(category.getName());
        return categoryRepository.save(oldCategory);
    }

}
