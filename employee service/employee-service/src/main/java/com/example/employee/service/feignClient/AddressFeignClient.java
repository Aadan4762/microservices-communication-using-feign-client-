package com.example.employee.service.feignClient;


import com.example.employee.service.DTO.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "http://localhost:1600/address-app/api/")
public interface AddressFeignClient {

    @GetMapping("/address/{id}")
    public ResponseEntity <AddressDto> getAddressByEmployeeId(@PathVariable("id") int id);



}
