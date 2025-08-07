package com.jobboard.dao;

import com.jobboard.model.Category;

import java.util.List;

public interface CategoryDao {
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
    Category createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}

