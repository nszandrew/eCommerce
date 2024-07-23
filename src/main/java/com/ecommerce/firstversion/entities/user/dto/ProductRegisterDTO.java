package com.ecommerce.firstversion.entities.user.dto;

import com.ecommerce.firstversion.entities.storage.ProductType;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterDTO(

        @NotNull String productName,
        @NotNull String productDescription,
        @NotNull String imageDirectory,
        @NotNull Double price,
        @NotNull Integer quantity,
        @NotNull ProductType type) {
}
