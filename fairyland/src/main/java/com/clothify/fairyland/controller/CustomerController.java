package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.entity.MyUserDetails;
import com.clothify.fairyland.entity.Users;
import com.clothify.fairyland.enumbers.Roles;
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
    public String addCustomer(@RequestBody Customer customer){
        List<Customer> customers=customerRepository.findAll();
        if(customers.isEmpty()){
            customer.setId(1);
        }else{
            for (int i=1;i<customers.size();i++){
                if (customers.get(i).getUsername().equals(customer.getUsername())){
                    return "User Already Exist";
                }  else {
                    Customer customer1=customers.get(customers.size()-1);
                    customer.setId(customer1.getId()+1);
                }
            }
        }

        customerRepository.save(customer);

        List<Users> users=usersRepository.findAll();
        usersRepository.save(new Users(users.isEmpty()?1:(users.get(users.size()-1).getId())+1,customer.getUsername(),customer.getPassword(),true,"ROLE_USER",customer.getId()));
        return "Done";
    }
    @GetMapping("/custList")
    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
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
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable(value = "id")Integer custId,@RequestBody Customer customer){

        Customer updateCustomer=customerRepository.getCustomerById(custId);
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
