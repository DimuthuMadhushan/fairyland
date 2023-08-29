package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.entity.OrderDetails;
import com.clothify.fairyland.entity.Orders;
import com.clothify.fairyland.repository.CustomerRepository;
import com.clothify.fairyland.repository.OrderDetailFRepository;
import com.clothify.fairyland.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
   @Autowired
   CustomerRepository customerRepository;
   @Autowired
    OrderDetailFRepository orderDetailRepository;

    @PostMapping("/add/{userName}")
    public Orders addOrders(@PathVariable(value = "userName")String userName,@RequestBody Orders order){
        List<Orders>orders=orderRepository.findAll();
        if(orders.isEmpty()){
            order.setId(1);
        }else{
            Orders orders1=orders.get(orders.size()-1);
            order.setId(orders1.getId()+1);
        }
        List<OrderDetails> orderDetails=order.getOrderDetailsList();
        List<OrderDetails>orderDetails1=orderDetailRepository.findAll();
        if(orderDetails1.isEmpty()){
            int i=0;
            while (i<orderDetails.size()){

                orderDetails.get(i).setId(++i);
            }
        }else {
            OrderDetails orderDetails2=orderDetails1.get(orderDetails1.size()-1);
            int index=orderDetails2.getId()+1;
            int i=0;
            while (i<orderDetails.size()){
                orderDetails.get(i).setId(index++);
                i++;
            }
        }
        orderRepository.save(order);

        Customer customer=customerRepository.getCustomerByUsername(userName);
        customer.getOrderList().add(order);
        customerRepository.save(customer);
        return order;
    }
    @GetMapping("/order-detail/{id}")
    public List<OrderDetails> getOrderDetails(@PathVariable(name = "id")Integer orderId){
        Orders order= orderRepository.getOrdersById(orderId);
        return order.getOrderDetailsList();
    }
    @GetMapping("/all-orders")
    public List<Orders> getOrdersList(){
        return orderRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public Orders deleteOrders(@PathVariable(value = "id")Integer orderId){
        Orders orders=orderRepository.getOrdersById(orderId);
        orderRepository.delete(orders);
        return orders;
    }
    @PutMapping("/{id}")
    public  Boolean updateOrders(@PathVariable(value="id")Integer orderId){
        Orders orders=orderRepository.getOrdersById(orderId);
        if(orders.getOrderStatus().equals("Not delivered")){
            orders.setOrderStatus("Delivered");
            return true;
        }
        return false;
    }
}
