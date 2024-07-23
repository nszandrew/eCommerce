package com.ecommerce.firstversion.entities.address.dto;

import jakarta.validation.constraints.NotNull;

public record AdressDTO(
        @NotNull(message = "O logradouro nao pode ser vazio")
        String logradouro,
        @NotNull(message = "O cep nao pode ser vazio")
        String cep,
        @NotNull(message = "O bairro nao pode ser vazio")
        String bairro,
        @NotNull(message = "O numero nao pode ser vazio")
        String numero,
        String complemento,
        @NotNull(message = "A cidade nao pode ser vazio")
        String cidade,
        @NotNull(message = "O UF nao pode ser vazio")
        String uf) {

    public AdressDTO(AdressDTO adressDTO){
        this(adressDTO.logradouro(), adressDTO.bairro(), adressDTO.cep(), adressDTO.numero(), adressDTO.complemento(), adressDTO.cidade(), adressDTO.uf());
    }
}
