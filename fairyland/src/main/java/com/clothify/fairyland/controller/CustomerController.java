package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    int count=0;
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){

        customer.setId(count++);
        customerRepository.save(customer);
        return customer;
    }
    @GetMapping
    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable(value = "id")Integer custId){
       Customer customer=customerRepository.getCustomerById(custId);
        if(customer!=null){
            customerRepository.delete(customer);
        }
        return customer;
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(value = "id")Integer custId,@RequestBody Customer customer){

        Customer updateCustomer=customerRepository.getCustomerById(custId);
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setFirstname(customer.getFirstname());
        updateCustomer.setHomeNumber(customer.getHomeNumber());
        updateCustomer.setLane(customer.getLane());
        updateCustomer.setLastname(customer.getLastname());
        updateCustomer.setPassword(customer.getPassword());
        updateCustomer.setPhone1(customer.getPhone1());
        updateCustomer.setPhone2(customer.getPhone2());
        updateCustomer.setPostalCode(customer.getPostalCode());
        updateCustomer.setTown(customer.getTown());
        updateCustomer.setUsername(customer.getUsername());

        customerRepository.save(updateCustomer);
        return updateCustomer;
    }
}
