package com.example.address.service.controller;


import com.example.address.service.DTO.AddressDto;
import com.example.address.service.entity.Address;
import com.example.address.service.service.serviceImplementation.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;
    
     public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAddress().stream().map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") int id){
        Address address = addressService.getAddressById(id);
       AddressDto addressResponse= modelMapper.map(address, AddressDto.class);
        return ResponseEntity.ok().body(addressResponse);

    }

    @PostMapping
    public  ResponseEntity <AddressDto> createAddress(@RequestBody AddressDto addressDto){
        //mapping Dto to entity
        Address addressRequest = modelMapper.map(addressDto, Address.class);
        Address address = addressService.createAddress(addressRequest);
        //mapping from entity to Dto
        AddressDto addressResponse = modelMapper.map(address, AddressDto.class);
        return new ResponseEntity<AddressDto>(addressResponse, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable int id, @RequestBody AddressDto addressDto) {

        // convert DTO to Entity
       Address addressRequest = modelMapper.map(addressDto, Address.class);
       Address address = addressService.updateAddress(id, addressRequest);
        // entity to DTO
        AddressDto  addressResponse = modelMapper.map(address, AddressDto.class);
        return ResponseEntity.ok().body(addressResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") int id){
        addressService.deleteAddress(id);
        return new ResponseEntity<String>("Address successfully deleted!", HttpStatus.OK);
    }
}

