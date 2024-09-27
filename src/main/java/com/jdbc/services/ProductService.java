package com.jdbc.services;

import com.jdbc.connection.DatabaseConnection;
import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.repository.ProductRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductRepo {
    private final String CHECK_CATEGORY = "Select * from category where id = ?";
    private final String INSERT_PRODUCT = "Insert into product(name,price,cat_id) values(?,?,?)";
    private final String SELECT_PRODUCT_10 = "SELECT * FROM Product ORDER BY id DESC LIMIT 10;";
    private final String UPDATE_PRODUCT = "Update product set name = ?,price = ?,cat_id = ? where id = ?";
    private final String DELETE_PRODUCT = "Delete from product where id = ?";
    private final String SELECT_PRODUCT_BY_PRICE_DOWN = "Select * from product order by cat_id,price desc";
    private final String SELECT_FOR_NAME = "Select product.* from product inner join category on product.cat_id = category.id where product.name like ? and category.name like ?";
    private final String SELECT_PRODUCT_BY_PRICE_UP = "Select * from product order by cat_id,price asc";


    @Override
    public boolean checkCategory(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_CATEGORY)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return false;
        }
    }

    @Override
    public void insertProduct(Product product) {
        if (checkCategory(product.getCat_id())) {
            try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setInt(3, product.getCat_id());
                if (statement.executeUpdate() > 0) {
                    System.out.println("Thêm thành công");
                } else {
                    System.out.println("Thất bại");
                }
            } catch (SQLException e) {
                DatabaseConnection.printSQLException(e);
            }
        } else {
            System.out.println("Thất bại không tìm thấy category");
        }
    }

    @Override
    public List<Product> getProduct10() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_10)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                products.add(new Product(id, name, price, cat_id));
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product, int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)
        ) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getCat_id());
            statement.setInt(4, id);
            if (checkCategory(product.getCat_id())) {
                if (statement.executeUpdate() > 0) {
                    System.out.println("sửa thành công");
                } else {
                    System.out.println("Thất bại");
                }
            } else {
                System.out.println("category không tồn tại");
            }
            ;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void deleteCategory(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public List<Product> getbyPriceDown() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_PRICE_DOWN)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                products.add(new Product(id, name, priceDown, cat_id));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }
    }

    @Override
    public List<Product> getbyPriceUp() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_PRICE_UP)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                products.add(new Product(id, name, priceDown, cat_id));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }
    }
    @Override
    public List<Product> getbyName(String name, String category) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_FOR_NAME)) {
            statement.setString(1,"%" + name + "%");
            statement.setString(2,"%" + category+ "%");
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                name = rs.getString("name");
                double price = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                products.add(new Product(id, name, price, cat_id));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}