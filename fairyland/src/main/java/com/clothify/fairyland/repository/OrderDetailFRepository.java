package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailFRepository extends JpaRepository<OrderDetails,Integer> {
    OrderDetails findOrderDetailsById();
}
