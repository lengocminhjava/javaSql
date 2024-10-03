package com.jdbc.baitapvenha.services;

import com.jdbc.baitapvenha.connection.DatabaseConnection;
import com.jdbc.baitapvenha.model.Category;
import com.jdbc.baitapvenha.repository.CategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements CategoryRepo {

    @Override
    public boolean save(Category category) {
        String insert = "INSERT INTO Category(name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                preparedStatement.setString(1, category.getName());
                preparedStatement.executeUpdate();
                return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        String update = "UPDATE Category SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Category findById(int id) {
        String query = "SELECT * FROM Category WHERE id = ?";
        try (Connection connection =DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) { // SELECT * FROM category WHERE id = ? => tiep theo phai biet thang ? la thang nao
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                int id_cate = rs.getInt("id");
                String name = rs.getString("name");
                category.setId(id_cate);
                category.setName(name);
                System.out.println(id + ", " + name);
                return category;
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return null;
    }

    @Override
    public List<Category> findCategoryByName(String name) {
        List<Category> result = new ArrayList<>();

        String query = "SELECT * FROM Category WHERE name like ?"; // ? <=> '%To%'
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // SELECT * FROM Category WHERE name like ? => tiep theo phai biet thang ? la thang nao
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                int id_cate = rs.getInt("id");
                String name_cate = rs.getString("name");
                category.setId(id_cate);
                category.setName(name_cate);
                result.add(category);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return result;
    }

    @Override
    public boolean checkIsExistCategory(String name) {
        String query = "SELECT * FROM Category WHERE name = ?";
        try (Connection connection =DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // SELECT * FROM category WHERE id = ? => tiep theo phai biet thang ? la thang nao
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                int id_cate = rs.getInt("id");
                String name_cate = rs.getString("name");
                category.setId(id_cate);
                category.setName(name_cate);
                return true;
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return false;
    }
}
