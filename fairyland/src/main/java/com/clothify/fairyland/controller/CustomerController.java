package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.entity.MyUserDetails;
import com.clothify.fairyland.entity.Users;
import com.clothify.fairyland.repository.CustomerRepository;
import com.clothify.fairyland.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UsersRepository usersRepository;
    @PostMapping("/add-customer")
    public Boolean addCustomer(@RequestBody Customer customer){
        List<Customer> customers=customerRepository.findAll();

        for (Customer value : customers) {
            if (value.getUsername().equals(customer.getUsername())) {
                return false;
            }
        }

        customerRepository.save(customer);

        List<Users> users=usersRepository.findAll();
        usersRepository.save(new Users(customer.getUsername(),customer.getPassword(),true,"ROLE_USER",customer.getId()));
        return true;
    }
    @GetMapping("/custList")
    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
    }
    @GetMapping("/detail/{username}")
    public Customer getCustomer(@PathVariable(value = "username")String username){
        return customerRepository.getCustomerByUsername(username);
    }
    @GetMapping("/custname/{id}")
    public String getCustName(@PathVariable(value = "id")Integer id){
        Customer customer=customerRepository.getCustomerById(id);
        return customer.getUsername();
    }
    @DeleteMapping("/delete/{id}")
    public Customer deleteCustomer(@PathVariable(value = "id")Integer custId){
       Customer customer=customerRepository.getCustomerById(custId);
        if(customer!=null){
            Optional<Users> users=usersRepository.findByUserName(customer.getUsername());
            users.orElseThrow(()->new UsernameNotFoundException("Not Found "));
            MyUserDetails userDetails=users.map(MyUserDetails::new).get();
            usersRepository.deleteById(userDetails.getId());
            customerRepository.delete(customer);
        }
        return customer;
    }
    @PutMapping("/update/{userName}")
    public Customer updateCustomer(@PathVariable(value = "userName")String username,@RequestBody Customer customer){

        Customer updateCustomer=customerRepository.getCustomerByUsername(username);
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setFirstname(customer.getFirstname());
        updateCustomer.setHomeNumber(customer.getHomeNumber());
        updateCustomer.setLane(customer.getLane());
        updateCustomer.setLastname(customer.getLastname());
        updateCustomer.setPhone1(customer.getPhone1());
        updateCustomer.setPhone2(customer.getPhone2());
        updateCustomer.setPostalCode(customer.getPostalCode());
        updateCustomer.setTown(customer.getTown());

        customerRepository.save(updateCustomer);
        return updateCustomer;
    }
}
