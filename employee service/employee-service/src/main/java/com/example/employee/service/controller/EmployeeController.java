package com.example.employee.service.controller;

import com.example.employee.service.DTO.AddressDto;
import com.example.employee.service.DTO.EmployeeDto;
import com.example.employee.service.entity.Employee;
import com.example.employee.service.feignClient.AddressFeignClient;
import com.example.employee.service.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressFeignClient addressFeignClient;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    //REST API to get List of Employees
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees().stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());

    }
    //REST API to get Employee By ID
    @GetMapping("/{id}")

   public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int id){
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
       // AddressDto addressDto = addressFeignClient.getAddressByEmployeeId(id).getBody();
        ResponseEntity<AddressDto> addressDtoResponseEntity = addressFeignClient.getAddressByEmployeeId(id);
        AddressDto addressDto = addressDtoResponseEntity.getBody();
        employeeResponse.setAddressResponse(addressDto);
        return ResponseEntity.ok().body(employeeResponse);

    }

    //Build Create Employee REST API
    @PostMapping
    public  ResponseEntity <EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = employeeService.createEmployee(employeeRequest);

        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<EmployeeDto>(employeeResponse,HttpStatus.CREATED);

    }
//Build update REST API
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
        // convert DTO to Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = employeeService.updateEmployee(id, employeeRequest);
        // entity to DTO
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);
    }
    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee successfully deleted!", HttpStatus.OK);
    }

}






















