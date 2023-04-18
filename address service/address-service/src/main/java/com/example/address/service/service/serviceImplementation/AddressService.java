package com.example.address.service.service.serviceImplementation;

import com.example.address.service.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddress();
    Address createAddress(Address address);
    Address updateAddress(int id, Address address);
    void deleteAddress(int id);
    Address getAddressById(int id);
}
