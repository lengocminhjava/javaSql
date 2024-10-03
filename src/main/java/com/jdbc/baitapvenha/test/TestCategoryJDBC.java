package com.jdbc.baitapvenha.test;

import com.jdbc.baitapvenha.model.Category;
import com.jdbc.baitapvenha.repository.CategoryRepo;
import com.jdbc.baitapvenha.services.CategoryService;

import java.util.List;

public class TestCategoryJDBC {
    public static void main(String[] args) {
        CategoryRepo categoryRepo = new CategoryService();
        Category category = categoryRepo.findById(5);
        System.out.println("===> Find Category By Id = 5");
        System.out.println(category);

        System.out.println("==> Find Category By Name like 'To' ");
        List<Category> categories = categoryRepo.findCategoryByName("To");
        System.out.println(categories);

        /*System.out.println("==> Test Insert Category");
        Category cate = new Category("New Category 22");
        if (categoryRepo.save(cate)) {
            System.out.println("===> Insert Category Successfully.");
        } else {
            System.out.println("===> Insert Category Failed.");
        }*/

        System.out.println("==> Test Update Category");
        Category cate_update = new Category(22, "Updated Category 22");
        if (categoryRepo.update(cate_update)) {
            System.out.println("===> Update Category Successfully.");
        } else {
            System.out.println("===> Update Category Failed.");
        }

    }
}