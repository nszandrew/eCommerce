package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.services.AdminService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductRegisterDTO> postProduct(@RequestBody @Valid ProductRegisterDTO data) {
        service.addProduct(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
