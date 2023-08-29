package com.clothify.fairyland.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private String orderStatus="Not delivered";
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id" ,referencedColumnName = "id")
    private List<OrderDetails>orderDetailsList;

    public Orders(Date date, String orderStatus, List<OrderDetails> orderDetailsList) {
        this.date = date;
        this.orderStatus = orderStatus;
        this.orderDetailsList = orderDetailsList;
    }

    public Orders() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
