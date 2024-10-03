package com.jdbc.baitapvenha.repository;

import com.jdbc.baitapvenha.model.Category;
import com.jdbc.baitapvenha.model.Product;

import java.util.List;

public interface ProductRepo {
    boolean insertProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    List<Product> getbyPriceDown();
    List<Product> getbyPriceUp();
    List<Product> getbyName(Product product, Category category);

}
