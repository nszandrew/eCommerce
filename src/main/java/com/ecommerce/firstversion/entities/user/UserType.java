package com.ecommerce.firstversion.entities.user;

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
    public void setType(String type){
        this.type = type;
    }
}
