package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entity.user.User;
import com.ecommerce.firstversion.entity.user.dto.*;
import com.ecommerce.firstversion.infra.mediatype.MediaType;
import com.ecommerce.firstversion.infra.security.TokenService;
import com.ecommerce.firstversion.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/register",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Transactional
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO data) {
        userService.createUser(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data) {

        var tokenResponse = userService.login(data);
        return new ResponseEntity<>(tokenResponse ,HttpStatus.OK);
    }

    @PutMapping(value = "/update",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Transactional
    public ResponseEntity<UserDataUpdateDTO> update(@RequestBody @Valid UserDataUpdateDTO data) {
        userService.updateUser(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value ="/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<Optional<UserDetailsDTO>> getAll(@PathVariable Long id) {
        var allUsers = userService.getAllUsers(id).map(UserDetailsDTO::new);
        return ResponseEntity.ok(allUsers);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
