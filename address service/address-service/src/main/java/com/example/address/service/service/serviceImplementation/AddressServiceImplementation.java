package com.example.address.service.service.serviceImplementation;

import com.example.address.service.entity.Address;
import com.example.address.service.exception.ResourceNotFoundException;
import com.example.address.service.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService{

    @Autowired
    private AddressRepository addressRepository;



    public AddressServiceImplementation(AddressRepository addressRepository) {
        super();
        this.addressRepository = addressRepository;


    }

    @Override
    public List<Address> getAddress() {
       return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(int id, Address address) {
        Address exstingAddress = addressRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Address", "id",id));
        exstingAddress.setZip(address.getZip());
        exstingAddress.setState(address.getState());
        exstingAddress.setLane1(address.getLane1());
        exstingAddress.setLane2(address.getLane2());
        return addressRepository.save(exstingAddress);
    }

    @Override
    public void deleteAddress(int id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("Address","id",id));
        addressRepository.deleteById(id);
    }


    @Override
    public Address getAddressById(int id) {
        Optional <Address> address = addressRepository.findById(id);
        if (address.isPresent()){
            return address.get();
        }else {
            throw  new ResourceNotFoundException("Address", "id",id);
        }
    }
}
