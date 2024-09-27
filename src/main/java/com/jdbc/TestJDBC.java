package com.jdbc;

import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.services.CategoryService;
import com.jdbc.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestJDBC {
    public static void main(String[] args) {
        CategoryService cs = new CategoryService();
        ProductService ps = new ProductService();
//  Thêm
//        Category c = new Category("Pro");
//        cs.addCategory(c);
//  Sửa
//        Category c = new Category("Pro1");
//        cs.updateCategory(c,3);
//  Xóa
//        cs.deleteCategory(3);

//  Search theo id
//        int id = 2;
//        if(cs.getCategoryById(id)==null){
//            System.out.println("Không có bản ghi nào");
//        }else{
//            System.out.println(cs.getCategoryById(id));
//        }

//  Search gần đúng name
//          String name = "M";
//          if(cs.getCategoryByName(name).size() == 0){
//              System.out.println("Không có bản ghi nào");
//          }
//          else {
//              System.out.println(cs.getCategoryByName(name));
//          }
//  selectAll
//        List<Category> categories = cs.getAllCategories();
//        if(categories.size() > 0){
//            for (Category c : categories) {
//                System.out.println(c);
//            }
//        }
//        else {
//            System.out.println("Không có bản ghi nào");
//        }
//
//  Thêm product và in ra 10 product
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhập category");
//        System.out.println("Nhập id");
//        int id = sc.nextInt();
//        Category category = cs.getCategoryById(id);
//        if(category == null){
//            System.out.println("category không tồn tại");
//        }else {
//        Product p = new Product("Minh", 100000, id);
//        ps.insertProduct(p);
//        List<Product> products = ps.getProduct10();
//        for (Product p1 : products) {
//            System.out.println(p1);
//           }
//        }
//  Sửa product
//        Product p = new Product("Oke",20000,2);
//        ps.updateProduct(p,12);
//  Xóa product
//        ps.deleteCategory(13);
//  giá giảm dần
//        List<Product> products = ps.getbyPriceDown();
//        for(Product product:products){
//            System.out.println(product);
//        }
//  Giá tăng dần
//          List<Product> products = ps.getbyPriceUp();
//          for(Product product:products){
//              System.out.println(product);
//          }
//  Tìm Kiếm Product theo tên và theo category name
            List<Product> products = ps.getbyName("Minh","tính");
            if(products.size()>0){
                for(Product product:products){
                    System.out.println(product);
                }
            }
            else {
                System.out.println("Không có dữ liệu");
            }


    }
}
