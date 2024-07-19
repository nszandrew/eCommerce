package com.ecommerce.firstversion.entity.user.dto;

import com.ecommerce.firstversion.entity.user.User;
import com.ecommerce.firstversion.entity.user.UserType;

public record UserDetailsDTO(Long id, String username, String email, String phone, UserType userType) {

    public UserDetailsDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getUserType());
    }
}
