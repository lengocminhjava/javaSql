package javaTest;

import java.sql.SQLException;

public class TestCRUDOperations {

    public static void main(String[] args) {
        InsertEmployee insertEmployee = new InsertEmployee();
        SelectEmployees selectEmployees = new SelectEmployees();
        UpdateEmployee updateEmployee = new UpdateEmployee();
        DeleteEmployee deleteEmployee = new DeleteEmployee();

        try {
            // Insert records
            insertEmployee.insertRecord("Ravi Kumar", "ravi.kumar@example.com", "India", 50000);
            insertEmployee.insertRecord("Sita Sharma", "sita.sharma@example.com", "India", 60000);
            insertEmployee.insertRecord("Rahul Jain", "rahul.jain@example.com", "India", 55000);

            // Select records
//            System.out.println("After inserting records:");
//            selectEmployees.selectAllRecords();

            // Update record
//            updateEmployee.updateRecord(1, "Ravi Kumar Singh", "ravi.kumar@example.com", "India", 55000);
////
//            // Select records
//            System.out.println("After updating record with ID 1:");
//            selectEmployees.selectAllRecords();
//
            // Delete record
//            deleteEmployee.deleteRecord(2);
//
//            // Select records
//            System.out.println("After deleting record with ID 3:");
//            selectEmployees.selectAllRecords();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}