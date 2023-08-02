package com.clothify.fairyland.entity;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    private Integer id;
    private String userName;
    private String passWord;
    private boolean active;
    private String role;
    private Integer customerId;

    public Users(Integer id, String userName, String passWord, boolean active,String role,Integer customerId) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.active = active;
        this.role=role;
        this.customerId=customerId;
    }

    public Users(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
