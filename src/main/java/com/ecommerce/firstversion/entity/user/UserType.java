package com.ecommerce.firstversion.entity.user;

public enum UserType {
    BUYER("buyer"),
    ADMIN("admin");

    private String type;

    UserType(String role){
        this.type = role;
    }

    public String getType(){
        return this.type;
    }
}
