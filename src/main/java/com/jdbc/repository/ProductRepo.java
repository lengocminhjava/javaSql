package com.jdbc.repository;

import com.jdbc.model.Category;
import com.jdbc.model.Product;

import java.util.List;

public interface ProductRepo {

    Category getCategory(Category category);

    boolean checkCategory(Category category);

    void insertProduct(Product product, Category category);

    List<Product> getProduct10();

    void updateProduct(Product product, Category category, int id);

    void deleteProductCateId(int id);

    void deleteProduct(int id);

    List<Product> getbyPriceDown();

    List<Product> getbyPriceUp();

    List<Product> getbyName(Product product, Category category);

    List<Product> getAllProducts();

}
