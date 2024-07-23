package com.ecommerce.firstversion.services;

import com.ecommerce.firstversion.entities.storage.Storage;
import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.repositories.StorageRepository;
import com.ecommerce.firstversion.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AdminService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());
    private final StorageRepository repository;
    private final SecurityUtil securityUtil;

    @Autowired
    public AdminService(StorageRepository repository, SecurityUtil securityUtil) {
        this.repository = repository;
        this.securityUtil = securityUtil;
    }

    public ProductRegisterDTO addProduct(ProductRegisterDTO data) {
        logger.info("Add a new product");
        boolean userInfo = securityUtil.verifyAdminPermissionsBoolean();
        Storage storage = new Storage(data.productName(), data.productDescription(), data.imageDirectory(), data.price(), data.quantity(), data.type());
        repository.save(storage);
        return data;
    }
}
