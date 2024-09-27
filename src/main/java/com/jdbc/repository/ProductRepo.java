package com.jdbc.repository;

import com.jdbc.model.Category;
import com.jdbc.model.Product;

import java.util.List;

public interface ProductRepo {
    boolean checkCategory(int id);
    void insertProduct(Product product);
    List<Product> getProduct10();
    void updateProduct(Product product,int id);
    void deleteCategory(int id);
    List<Product> getbyPriceDown();
    List<Product> getbyPriceUp();
    List<Product> getbyName(String name, String category);
    List<Product> getAllProducts();

}
