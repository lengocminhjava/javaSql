package com.jdbc.repository;

import com.jdbc.model.Category;

import java.util.List;

public interface CategoryRepo {

    Category getCategory(Category category);

    boolean checkCategory(Category category);

    void addCategory(Category category);

    void updateCategory(Category category, int id);

    void deleteCategory(int id);

    Category getCategoryById(int id);

    List<Category> getCategoryByName(String name);

    List<Category> getAllCategories();
}
