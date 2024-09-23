package com.rs;

import com.rs.model.Employee;
import com.rs.repository.EmployeeRepos;
import com.rs.service.EmployeeService;

import java.sql.SQLException;

public class test {


    public static void main(String[] args) {
        EmployeeRepos employeeRepos = new EmployeeService();
        // Insert records
//        Employee employee = new Employee("Pro","minhokmen1@gmail.com","abc",123);
//        employeeRepos.insert(employee);
//        System.out.println(employeeRepos.getEmployee());
//        Employee employee = new Employee(4,"Pro","minhokmen1@gmail.com","abc",123);
//        employeeRepos.update(employee);
//        System.out.println(employeeRepos.getEmployee());
        employeeRepos.delete(10);
        System.out.println(employeeRepos.getEmployee());
    }




}
