package com.jdbc.services;

import com.jdbc.connection.DatabaseConnection;
import com.jdbc.model.Category;
import com.jdbc.repository.CategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryService implements CategoryRepo {
    private final String SELECt_ALL_CATEGORIES = "select * from category";
    private final String SELECT_CATEGORY_BY_ID = "select * from category where id = ?";
    private final String SELECT_CATEGORY_BY_NAME = "select * from category where name = ?";
    private final String INSERT_CATEGORY = "insert into category(name) values(?)";
    private final String UPDATE_CATEGORY = "update category set name=? where id=?";
    private final String DELETE_CATEGORY = "delete from category where id = ?";


    @Override
    public Category getCategory(Category category) {
        try {
            Category category2 = null;
            if (!getAllCategories().contains(category)) {
                addCategory(category);
            }
            List<Category> category1 = getCategoryByName(category.getName());
            Set<Category> categorySet = new HashSet<>(category1);
            for (Category c : categorySet) {
                category2 = c;
            }
            return category2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkCategory(Category category) {
        try {
            return getAllCategories().contains(category);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addCategory(Category category) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY)) {
            ProductService productService = new ProductService();
            if (!checkCategory(category)) {
                statement.setString(1, category.getName());
                if (statement.executeUpdate() > 0) {
                    System.out.println("Thêm thành công!");
                } else {
                    System.out.println("Thất bại");
                }
            } else {
                System.out.println("Tên đã tồn tại");
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void updateCategory(Category category, int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
            ProductService productService = new ProductService();
            if (!checkCategory(category)) {
                statement.setString(1, category.getName());
                statement.setInt(2, id);
                if (statement.executeUpdate() > 0) {
                    System.out.println("Sửa thành công!");
                } else {
                    System.out.println("Thất bại");
                }
            } else {
                System.out.println("Tên đã tồn tại");
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        ;
    }

    @Override
    public void deleteCategory(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)) {
            CategoryService categoryService = new CategoryService();
            Category category = categoryService.getCategoryById(id);
            ProductService productService = new ProductService();
            if (checkCategory(category)) {
                productService.deleteProductCateId(id);
                statement.setInt(1, id);
                if (statement.executeUpdate() > 0) {
                    System.out.println("Xóa thành công");
                } else {
                    System.out.println("Thất bại");
                }
            } else {
                System.out.println("Không có id mà xoá");
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = null;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return category;
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return categories;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECt_ALL_CATEGORIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return categories;
    }
}
