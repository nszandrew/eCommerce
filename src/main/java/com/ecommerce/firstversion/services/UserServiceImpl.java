package com.ecommerce.firstversion.services;

import com.ecommerce.firstversion.entities.user.User;
import com.ecommerce.firstversion.entities.user.UserType;
import com.ecommerce.firstversion.entities.user.dto.*;
import com.ecommerce.firstversion.configuration.security.TokenService;
import com.ecommerce.firstversion.repositories.UserRepository;
import com.ecommerce.firstversion.services.interfaces.UserService;
import com.ecommerce.firstversion.utils.securityutil.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SecurityUtil securityUtil;

    @Autowired
    public UserServiceImpl(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService, SecurityUtil securityUtil) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.securityUtil = securityUtil;
    }

    @Override
    public UserRegisterDTO createUser(UserRegisterDTO data) {
        logger.info("Creating new user");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword , data.cpf(), data.email(), data.phone());
        repository.save(newUser);
        return data;
    }

    @Override
    public UserAuthResponseDTO login (UserLoginDTO data){
        logger.info("Logging user");
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new UserAuthResponseDTO(token);
    }

    @Override
    public UserDataUpdateDTO updateUser(UserDataUpdateDTO data) {
        logger.info("Updating one user");
        var user = repository.getReferenceById(data.id());
        user.updateUser(data);

        return data;
    }

    @Override
    public Optional<User> getAllUsers(Long id) {
        logger.info("Getting all users");
        return repository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting a user");
        repository.deleteById(id);
    }
}

