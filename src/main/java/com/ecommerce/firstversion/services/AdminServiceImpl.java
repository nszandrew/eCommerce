package com.ecommerce.firstversion.services;

import com.ecommerce.firstversion.entities.storage.Storage;
import com.ecommerce.firstversion.entities.user.User;
import com.ecommerce.firstversion.entities.user.UserType;
import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.entities.user.dto.UserToAdminDTO;
import com.ecommerce.firstversion.repositories.StorageRepository;
import com.ecommerce.firstversion.repositories.UserRepository;
import com.ecommerce.firstversion.services.interfaces.AdminService;
import com.ecommerce.firstversion.utils.securityutil.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AdminServiceImpl implements AdminService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final StorageRepository repository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private User user;

    @Autowired
    public AdminServiceImpl(StorageRepository repository, UserRepository userRepository, SecurityUtil securityUtil) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
    }

    @Override
    public ProductRegisterDTO addProduct(ProductRegisterDTO data) {
        logger.info("Add a new product");
        securityUtil.verifyAdminPermissions();
        Storage storage = new Storage();
        BeanUtils.copyProperties(data, storage);
        repository.save(storage);
        return data;
    }

    @Override
    public void updateUserToAdmin(UserToAdminDTO data) {
        logger.info("Updating buyer to admin");
        User currentUserName = securityUtil.verifyAdminPermissions();
        var user = userRepository.findById(data.id()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!(user.getUserType() == UserType.BUYER)){
            throw new RuntimeException("User is not a Buyer!");
        }
        user.updateRole(data);
    }
}
