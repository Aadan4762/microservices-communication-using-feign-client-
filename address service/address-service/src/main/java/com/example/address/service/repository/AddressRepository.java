package com.example.address.service.repository;

import com.example.address.service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true,value = "SELECT ea.id,ea.lane1,ea.lane2,ea.state,ea.zip  FROM AdanaliNew.address ea join AdanaliNew.employee e on e.id = ea.employee_id where ea.employee_id=employeeId")
    Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
