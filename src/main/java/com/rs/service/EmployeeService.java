package com.rs.service;

import com.rs.connection.DatabaseConnection;
import com.rs.model.Employee;
import com.rs.repository.EmployeeRepos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeRepos {
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees;";
    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employees (name, email, country, salary) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_EMPLOYEES_SQL = "UPDATE employees SET name = ?, email = ?, country = ?, salary = ? WHERE id = ?;";
    private static final String DELETE_EMPLOYEES_SQL = "DELETE FROM employees WHERE id = ?;";
    @Override
    public void insert(Employee employee) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getCountry());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getCountry());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEES_SQL)) {
            preparedStatement.setInt(1, id);
            if ( preparedStatement.executeUpdate() > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để xóa.");
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }

    @Override
    public List<Employee> getEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                double salary = rs.getDouble("salary");
                Employee employee = new Employee(id, name, email, country, salary);
                employees.add(employee);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
        return employees;
    }
}
