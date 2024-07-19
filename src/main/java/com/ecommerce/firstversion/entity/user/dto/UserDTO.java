package com.ecommerce.firstversion.entity.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank(message = "O Username nao pode ser vazio") String fullName,
        @NotBlank(message = "A senha nao pode ser vazio") String password,
        @Email @NotNull(message = "O email nao pode ser vazio e precisa seguir o padrao de email example@dominio.com") String email,
        @Pattern(regexp = "\\d{11}") @NotBlank(message = "O cpf nao pode ser vazio, e precisa ter no minimo 11 digitos") String cpf,
        @NotBlank(message = "O telefone nao pode ser vazio") String phone) {
}
