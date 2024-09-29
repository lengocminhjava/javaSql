package com.jdbc;

import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.repository.ProductRepo;
import com.jdbc.services.CategoryService;
import com.jdbc.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestJDBC {
    private static List<Product> products;

    public static void main(String[] args) {
        CategoryService cs = new CategoryService();
/*    ProductService ps = new ProductService();
        Category c = new Category("Ơ");
        cs.addCategory(c);*/
//         Sửa
/*        Category c = new Category("tivi");
        cs.updateCategory(c,11);*/
/*  Xóa
        cs.deleteCategory(3);*/

/*  Search theo id
        int id = 2;
        if(cs.getCategoryById(id)==null){
            System.out.println("Không có bản ghi nào");
        }else{
            System.out.println(cs.getCategoryById(id));
        }
*/

/*
  Search gần đúng name
          String name = "M";
          if(cs.getCategoryByName(name).size() == 0){
              System.out.println("Không có bản ghi nào");
          }
          else {
              System.out.println(cs.getCategoryByName(name));
          }
*/


/*  giá giảm dần
        List<Product> products = ps.getbyPriceDown();
        for(Product product:products){
            System.out.println(product);
        }
  Giá tăng dần
          List<Product> products = ps.getbyPriceUp();
          for(Product product:products){
              System.out.println(product);
          }
*/
//  Tìm Kiếm Product theo tên và theo category name
/*        Category category = new Category("O");
        Product product1 = new Product("e", 123, category);
        List<Product> products = ps.getbyName(product1, category);
        if (products.size() > 0) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không có dữ liệu");
        }
*/
//insert , update Product
/*      ProductService pss = new ProductService();
        Category c1 = new Category("Ơ");
        pss.updateProduct(new Product("Ơ", 122), c1, 26);
        CategoryService css = new CategoryService();
        css.deleteCategory(12);
 */
    }
}
