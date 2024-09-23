package com.rs.repository;

import com.rs.model.Employee;

import java.util.List;

public interface EmployeeRepos {
    void insert(Employee employee);
    void update(Employee employee);
    void delete(int id);
    List<Employee> getEmployee();
}
