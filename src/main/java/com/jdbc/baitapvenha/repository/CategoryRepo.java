package com.jdbc.baitapvenha.repository;

import com.jdbc.baitapvenha.model.Category;

import java.util.List;

public interface CategoryRepo {
    boolean save(Category category);
    boolean update(Category category);
    void delete(Category category);
    Category findById(int id);
    List<Category> findCategoryByName(String name);
    boolean checkIsExistCategory(String name);
}
