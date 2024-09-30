package com.jdbc.services;

import com.jdbc.connection.DatabaseConnection;
import com.jdbc.model.Category;
import com.jdbc.model.Product;
import com.jdbc.repository.ProductRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService implements ProductRepo {
    private final String INSERT_PRODUCT = "Insert into product(name,price,cat_id) values(?,?,?)";
    private final String SELECT_PRODUCT_10 = "SELECT * FROM Product ORDER BY id DESC LIMIT 10;";
    private final String UPDATE_PRODUCT = "Update product set name = ?,price = ?,cat_id = ? where id = ?";
    private final String DELETE_PRODUCT = "Delete from product where id = ?";
    private final String DELETE_PRODUCT_CAT_ID = "Delete from product where cat_id = ?";
    private final String SELECT_PRODUCT_BY_PRICE_DOWN = "Select * from product order by cat_id,price desc";
    private final String SELECT_FOR_NAME = "Select product.* from product inner join category on product.cat_id = category.id where product.name like ? and category.name like ?";
    private final String SELECT_PRODUCT_BY_PRICE_UP = "Select * from product order by cat_id,price asc";
    private final String SELECT_ALL_PRODUCT = "Select * from product ";

    @Override
    public boolean checkProduct(Product product) {
        try {
            return getAllProducts().contains(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void insertProduct(Product product, Category category) {

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)) {
            CategoryService categoryService = new CategoryService();
            if (!(checkProduct(product) && categoryService.checkCategory(category))) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                if (categoryService.getCategory(category) == null) {
                    statement.setNull(3, Types.INTEGER);
                } else {
                    statement.setInt(3, categoryService.getCategory(category).getId());
                }
                if (statement.executeUpdate() > 0) {
                    System.out.println("Thêm thành công");
                } else {
                    System.out.println("Thất bại");
                }

            } else {
                System.out.println("Tên và category đã tồn tại");
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public List<Product> getProduct10() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_10)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getCategoryById(cat_id);
                products.add(new Product(id, name, price, category));
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product, Category category, int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            CategoryService categoryService = new CategoryService();
            if (!(checkProduct(product) && categoryService.checkCategory(category))) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                if (categoryService.getCategory(category) == null) {
                    statement.setNull(3, Types.INTEGER);
                } else {
                    statement.setInt(3, categoryService.getCategory(category).getId());
                }
                statement.setInt(4, id);
                if (statement.executeUpdate() > 0) {
                    System.out.println("sửa thành công");
                } else {
                    System.out.println("Thất bại");
                }
            }
            else {
                System.out.println("Tên và category đã tồn tại");
            }

        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }

    }

    public void deleteProductCateId(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_CAT_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
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
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_PRICE_DOWN)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getCategoryById(cat_id);
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
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_PRICE_UP)) {
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double priceDown = rs.getDouble("price");
                int cat_id = rs.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getCategoryById(cat_id);
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
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_FOR_NAME)) {
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
                Category category1 = categoryService.getCategoryById(cat_id);
                products.add(new Product(id, name, price, category1));
            }
            return products;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
            return null;
        }

    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int cat_id = resultSet.getInt("cat_id");
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getCategoryById(cat_id);
                Product product = new Product(id, name, price, category);
                products.add(product);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return products;
    }
}
