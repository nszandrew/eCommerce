package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entities.user.dto.*;
import com.ecommerce.firstversion.services.interfaces.UserService;
import com.ecommerce.firstversion.utils.mediatype.MediaType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/register",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Transactional
    public ResponseEntity<UserRegisterDTO> register(@RequestBody @Valid UserRegisterDTO data) {
        userService.createUser(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data) {

        var tokenResponse = userService.login(data);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/update",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Transactional
    public ResponseEntity<UserDataUpdateDTO> update(@RequestBody @Valid UserDataUpdateDTO data) {
        userService.updateUser(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Optional<UserDetailsDTO>> getAll(@PathVariable Long id) {
        var allUsers = userService.getAllUsers(id).map(UserDetailsDTO::new);
        return ResponseEntity.ok(allUsers);
    }

    @PutMapping("/user/admin")
    @Transactional
    public ResponseEntity<UserToAdminDTO> updateUserToAdmin(@RequestBody @Valid UserToAdminDTO data) {
        userService.updateUserToAdmin(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
