package com.jobboard.controllers;

import com.jobboard.dao.CategoryDao;
import com.jobboard.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @PostMapping
    public void createCategory(@RequestBody Category category) {
        categoryDao.createCategory(category);
    }
}

