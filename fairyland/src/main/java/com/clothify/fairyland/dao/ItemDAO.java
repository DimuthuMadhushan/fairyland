package com.clothify.fairyland.dao;

import com.clothify.fairyland.enumbers.Category;
import org.springframework.web.multipart.MultipartFile;

public class ItemDAO {
    private Category category;
    private Integer xLQuantity;
    private Integer lQuantity;
    private Integer mQuantity;
    private Integer sQuantity;
    private Double unitPrice;
    private  String tittle;
    private MultipartFile image;

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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
