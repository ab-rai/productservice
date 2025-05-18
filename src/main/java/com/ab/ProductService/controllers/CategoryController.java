package com.ab.ProductService.controllers;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Category;
import com.ab.ProductService.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id) throws ProductNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new ProductNotFoundException("Category not found for id:"+id);
    }
}
