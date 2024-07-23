package com.ecommerce.firstversion.entities.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDataUpdateDTO(
        @NotNull Long id,
        String fullName,
        String password,
        @Column(unique = true) @Pattern(regexp = "\\d{11}")  String cpf,
        @Column(unique=true) @Email String email,
        @Pattern(regexp = "\\d{12}")  String phone) {
}
