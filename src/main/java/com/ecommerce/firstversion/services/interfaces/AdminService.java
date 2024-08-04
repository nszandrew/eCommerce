package com.ecommerce.firstversion.services.interfaces;

import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.entities.user.dto.UserToAdminDTO;

public interface AdminService {

    ProductRegisterDTO addProduct(ProductRegisterDTO data);

    void updateUserToAdmin(UserToAdminDTO data);
}
