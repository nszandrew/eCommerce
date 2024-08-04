package com.ecommerce.firstversion.controllers;

import com.ecommerce.firstversion.entities.user.dto.ProductRegisterDTO;
import com.ecommerce.firstversion.entities.user.dto.UserToAdminDTO;
import com.ecommerce.firstversion.services.interfaces.AdminService;
import com.ecommerce.firstversion.utils.mediatype.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Admin", description = "Endpoints for Admin People")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                 consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Post a Product", description = "Post a product", tags = {"Admin"},
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductRegisterDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @Transactional
    public ResponseEntity<ProductRegisterDTO> postProduct(@RequestBody @Valid ProductRegisterDTO data) {
        service.addProduct(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PutMapping("/user/admin")
    @Operation(summary = "Update User to Admin", description = "Update User to Admin", tags = {"Admin"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserToAdminDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @Transactional
    public ResponseEntity<UserToAdminDTO> updateUserToAdmin(@RequestBody @Valid UserToAdminDTO data) {
        service.updateUserToAdmin(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
