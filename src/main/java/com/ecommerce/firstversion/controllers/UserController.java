package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDetailsDTO;
import com.ecommerce.firstversion.infra.mediatype.MediaType;
import com.ecommerce.firstversion.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/register",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Transactional
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO data) {
        var user = userService.createUser(data);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
