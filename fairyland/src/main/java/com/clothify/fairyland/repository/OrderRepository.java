package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders getOrdersById(Integer id);
}
