package com.example.employee.service.service;

import com.example.employee.service.Repository.EmployeeRepository;
import com.example.employee.service.entity.Employee;
import com.example.employee.service.exception.ResourceNotFoundException;
import com.example.employee.service.feignClient.AddressFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;



    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee employees = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
        employees.setName(employee.getName());
        employees.setEmail(employee.getEmail());
        employees.setBloodgroup(employee.getBloodgroup());
        return employeeRepository.save(employees);
    }

    @Override
    public void deleteEmployee(int id) {
       Employee employee = employeeRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
       employeeRepository.delete(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional <Employee> result = employeeRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }else {
            throw new ResourceNotFoundException("Employee", "id",id);
        }
    }
}
