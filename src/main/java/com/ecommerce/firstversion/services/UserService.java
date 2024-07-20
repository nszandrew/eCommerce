package com.ecommerce.firstversion.services;

import com.ecommerce.firstversion.entity.user.User;
import com.ecommerce.firstversion.entity.user.dto.UserAuthResponseDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.entity.user.dto.UserLoginDTO;
import com.ecommerce.firstversion.infra.security.TokenService;
import com.ecommerce.firstversion.repositorys.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository repository;
    private final Logger logger = Logger.getLogger(UserService.class.getName());
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserService(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public UserDTO createUser(UserDTO data) {
        logger.info("Creating new user");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword , data.cpf(), data.email(), data.phone());
        repository.save(newUser);
        return data;
    }

    public UserAuthResponseDTO login (UserLoginDTO data){
        logger.info("Logging user");
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new UserAuthResponseDTO(token);
    }

    public UserDataUpdateDTO updateUser(UserDataUpdateDTO data) {
        logger.info("Updating one user");
        var user = repository.getReferenceById(data.id());
        user.updateUser(data);

        return data;
    }

    public Optional<User> getAllUsers(Long id) {
        logger.info("Getting all users");
        return repository.findById(id);
    }

    public void deleteUser(Long id) {
        logger.info("Deleting a user");
        repository.deleteById(id);
    }
}
