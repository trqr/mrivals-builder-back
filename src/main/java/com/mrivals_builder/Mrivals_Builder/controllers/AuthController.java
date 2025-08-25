package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }
}
