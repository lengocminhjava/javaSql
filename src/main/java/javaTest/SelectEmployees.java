package javaTest;

import java.sql.*;

public class SelectEmployees {

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public void selectAllRecords() throws SQLException {
        // Establishing a Connection
        JDBCUtils JDBCUtils = null;
        try (Connection connection = JDBCUtils.getConnection();
             // Creating a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            // Executing the query and getting the result set
            ResultSet rs = preparedStatement.executeQuery();
            // Processing the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                double salary = rs.getDouble("salary");
                System.out.println(id + ", " + name + ", " + email + ", " + country + ", " + salary);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}