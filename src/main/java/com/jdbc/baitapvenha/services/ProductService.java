package com.jdbc.baitapvenha.services;

import com.jdbc.baitapvenha.connection.DatabaseConnection;
import com.jdbc.baitapvenha.model.Category;
import com.jdbc.baitapvenha.model.Product;
import com.jdbc.baitapvenha.repository.ProductRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductRepo {
    @Override
    public boolean insertProduct(Product product) {
        String addProductQuery = "INSERT INTO Product(name, price, cat_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addProductQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getCategory().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        String addProductQuery = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addProductQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public List<Product> getbyPriceDown() {
        String query = "Select * from product order by cat_id,price desc";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.findById(cat_id);
                products.add(new Product(id, name, priceDown, category));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }
    }

    @Override
    public List<Product> getbyPriceUp() {
        String query = "Select * from product order by cat_id,price asc";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.findById(cat_id);
                products.add(new Product(id, name, priceDown, category));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }
    }

    @Override
    public List<Product> getbyName(Product product, Category category) {
        String query = "Select product.* from product inner join category on product.cat_id = category.id where product.name like ? and category.name like ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + product.getName() + "%");
            statement.setString(2, "%" + category.getName() + "%");
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category1 = categoryService.findById(cat_id);
                products.add(new Product(id, name, price, category1));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }

    }

}
