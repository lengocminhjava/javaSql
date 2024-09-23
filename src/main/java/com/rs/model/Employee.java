package com.rs.model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String country;
    private double salary;
    public Employee() {
    }
    public Employee(String name, String email, String country, double salary) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.salary = salary;
    }
    public Employee(int id, String name, String email, String country, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", salary=" + salary +
                '}';
    }
}
