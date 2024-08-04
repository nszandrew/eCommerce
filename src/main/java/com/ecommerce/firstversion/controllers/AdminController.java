package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.services.interfaces.AdminService;
import com.ecommerce.firstversion.utils.mediatype.MediaType;
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

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                 consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Transactional
    public ResponseEntity<ProductRegisterDTO> postProduct(@RequestBody @Valid ProductRegisterDTO data) {
        service.addProduct(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
