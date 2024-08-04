package com.ecommerce.firstversion.services.interfaces;

import com.ecommerce.firstversion.entities.user.User;
import com.ecommerce.firstversion.entities.user.dto.*;

import java.util.Optional;

public interface UserService {

    UserRegisterDTO createUser(UserRegisterDTO data);

    UserAuthResponseDTO login (UserLoginDTO data);

    UserDataUpdateDTO updateUser(UserDataUpdateDTO data);

    Optional<User> getAllUsers(Long id);

    void deleteUser(Long id);

    void updateUserToAdmin(UserToAdminDTO data);
}
