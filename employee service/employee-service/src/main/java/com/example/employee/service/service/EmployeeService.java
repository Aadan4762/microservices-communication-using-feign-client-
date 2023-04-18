package com.example.employee.service.service;

import com.example.employee.service.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
    Employee getEmployeeById(int id);
}
