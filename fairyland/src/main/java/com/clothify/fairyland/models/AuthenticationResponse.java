package com.clothify.fairyland.models;

public class AuthenticationResponse {
    private final String jwt;
    private final Integer id;
    private final String role;

    public AuthenticationResponse(String jwt, Integer id,String role){
        this.jwt=jwt;
        this.id = id;
        this.role=role;
    }

    public String getJwt() {
        return jwt;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

}
