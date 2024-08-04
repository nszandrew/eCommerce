package com.ecommerce.firstversion.services.interfaces;

import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;

public interface AdminService {

    ProductRegisterDTO addProduct(ProductRegisterDTO data);
}
