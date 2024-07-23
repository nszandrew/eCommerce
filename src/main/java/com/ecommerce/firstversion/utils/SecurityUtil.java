package com.ecommerce.firstversion.utils;

import com.ecommerce.firstversion.entities.user.User;
import com.ecommerce.firstversion.entities.user.UserType;
import com.ecommerce.firstversion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private final UserRepository repository;

    @Autowired
    public SecurityUtil(UserRepository repository) {
        this.repository = repository;
    }

    public User verifyAdminPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = repository.findByLogin(currentUsername);

        if (!currentUser.getUserType().equals(UserType.ADMIN)) {
            throw new AccessDeniedException("You do not have permission to perform this operation.");
        }

        return currentUser;
    }

    public boolean verifyAdminPermissionsBoolean() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = repository.findByLogin(currentUsername);

        if (!currentUser.getUserType().equals(UserType.ADMIN)) {
            throw new AccessDeniedException("You do not have permission to perform this operation.");
        }

        return true;
    }
}
