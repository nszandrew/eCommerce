package com.ecommerce.firstversion.entity.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record UserDataUpdateDTO(
        @NotNull Long id,
                                String fullName,
                                String password,
                                String cpf,
        @Column(unique=true)    String email,
                                String phone) {
}
