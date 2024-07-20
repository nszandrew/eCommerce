package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDetailsDTO;
import com.ecommerce.firstversion.entity.user.dto.UserLoginDTO;
import com.ecommerce.firstversion.infra.mediatype.MediaType;
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


    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/register",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Transactional
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO data) {
        if(data.email() != null && !data.email().isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var user = userService.createUser(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);

        return ResponseEntity.ok().build();
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
