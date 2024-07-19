package com.ecommerce.firstversion.services;

import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.exceptions.customized.UserNotFoundException;
import com.ecommerce.firstversion.repositorys.UserRepository;
import com.ecommerce.firstversion.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository repository;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO createUser(UserDTO data) {
        logger.info("Creating new user");
        User infos = new User(data);
        repository.save(infos);
        return data;
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
