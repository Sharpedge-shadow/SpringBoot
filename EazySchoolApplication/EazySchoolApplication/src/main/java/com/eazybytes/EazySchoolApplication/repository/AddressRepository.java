package com.eazybytes.EazySchoolApplication.repository;

import com.eazybytes.EazySchoolApplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}