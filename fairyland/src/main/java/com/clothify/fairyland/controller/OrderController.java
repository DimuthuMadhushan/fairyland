package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.entity.OrderDetails;
import com.clothify.fairyland.entity.Orders;
import com.clothify.fairyland.repository.CustomerRepository;
import com.clothify.fairyland.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    int count=0;
    int index=0;
    @Autowired
    OrderRepository orderRepository;
   @Autowired
   CustomerRepository customerRepository;

    @PostMapping("/{custid}")
    public Orders addOrders(@PathVariable(value = "custid")Integer custId,@RequestBody Orders order){
        order.setId(count++);
        List<OrderDetails> orderDetails=order.getOrderDetailsList();
        int i=0;
        while (i<orderDetails.size()){
            orderDetails.get(i).setId(index++);
            i++;
        }
        orderRepository.save(order);

     Customer customer=customerRepository.getCustomerById(custId);
       customer.getOrderList().add(order);
       customerRepository.save(customer);
        return order;
    }
    @GetMapping("/{id}")
    public List<OrderDetails> getOrderDetails(@PathVariable(name = "id")Integer orderId){
        Orders order= orderRepository.getOrdersById(orderId);
        return order.getOrderDetailsList();
    }
    @GetMapping
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
    public  Orders updateOrders(@PathVariable(value="id")Integer orderId,@RequestBody Orders orders){
        Orders updateOrder=orderRepository.getOrdersById(orderId);
        updateOrder.setDate(orders.getDate());
        orderRepository.save(updateOrder);
        return  updateOrder;
    }
}
