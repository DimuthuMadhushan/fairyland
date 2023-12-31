package com.clothify.fairyland.entity;

import com.clothify.fairyland.enumbers.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    private Category category;
    private Integer xLQuantity;

    public Item(){}

    public Item(Category category, Integer xLQuantity, Integer lQuantity, Integer mQuantity, Integer sQuantity, Double unitPrice, String imgUrl, String tittle) {
        this.category = category;
        this.xLQuantity = xLQuantity;
        this.lQuantity = lQuantity;
        this.mQuantity = mQuantity;
        this.sQuantity = sQuantity;
        this.unitPrice = unitPrice;
        this.imgUrl = imgUrl;
        this.tittle = tittle;
    }

    private Integer lQuantity;
    private Integer mQuantity;
    private Integer sQuantity;
    private Double unitPrice;
    private String imgUrl;
    private  String tittle;



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getxLQuantity() {
        return xLQuantity;
    }

    public void setxLQuantity(Integer xLQuantity) {
        this.xLQuantity = xLQuantity;
    }

    public Integer getlQuantity() {
        return lQuantity;
    }

    public void setlQuantity(Integer lQuantity) {
        this.lQuantity = lQuantity;
    }

    public Integer getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(Integer mQuantity) {
        this.mQuantity = mQuantity;
    }

    public Integer getsQuantity() {
        return sQuantity;
    }

    public void setsQuantity(Integer sQuantity) {
        this.sQuantity = sQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}

