package com.clothify.fairyland.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id" ,referencedColumnName = "id")
    private List<OrderDetails>orderDetailsList;


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
}
