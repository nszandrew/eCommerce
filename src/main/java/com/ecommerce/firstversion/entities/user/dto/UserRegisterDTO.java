package com.ecommerce.firstversion.entities.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDTO(
        @NotBlank(message = "O Username nao pode ser vazio")@Column(unique = true) String login,
        @NotBlank(message = "A senha nao pode ser vazio") String password,
        @Email @NotNull (message = "O email nao pode ser vazio e precisa seguir o padrao example@dominio.com")
        @Column(unique = true)
        String email,
        @Pattern(regexp = "\\d{11}") @NotBlank(message = "O CPF nao pode ser vazio, e precisa ter no minimo 11 digitos")
        @Column(unique = true)
        String cpf,
        @NotBlank(message = "O Telefone nao pode ser vazio") @Column(unique = true) String phone) {
}
