package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer getCustomerById(Integer id);
}
