package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.LoginRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return new ResponseEntity<>(authService.login(request.email(), request.password()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        return new ResponseEntity<>(authService.getCurrentUser(principal), HttpStatus.OK);
    }
}
