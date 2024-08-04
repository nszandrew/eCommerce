package com.ecommerce.firstversion.utils.securityutil;

import com.ecommerce.firstversion.entities.user.User;
import com.ecommerce.firstversion.entities.user.UserType;
import com.ecommerce.firstversion.exceptions.customized.WeakPasswordException;
import com.ecommerce.firstversion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SecurityUtil {

    private final UserRepository repository;
    private static final List<String> COMMON_PASSWORDS = Arrays.asList("123456", "password", "12345678", "qwerty", "abc123", "111111", "123456789", "1234567", "password1", "12345");
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Autowired
    public SecurityUtil(UserRepository repository) {
        this.repository = repository;
    }

    public boolean verifyPassword(String password) {
        if (COMMON_PASSWORDS.contains(password)) {
            throw new WeakPasswordException("Weak password or invalid characters");
        }
        return pattern.matcher(password).matches();
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
}
