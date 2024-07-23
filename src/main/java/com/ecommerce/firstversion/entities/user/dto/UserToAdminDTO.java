package com.ecommerce.firstversion.entities.user.dto;

import com.ecommerce.firstversion.entities.user.UserType;

public record UserToAdminDTO (Long id, String email, UserType userType){
}
