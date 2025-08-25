package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponseDTO register(RegisterRequestDTO request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        User created = new User();
        created.setEmail(request.getEmail());
        created.setUsername(request.getUsername());
        created.setPassword(encryptedPassword);
        User saved = userRepository.save(created);

        UserDTO userDTO = new UserDTO(saved);
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setUser(userDTO);
        response.setToken("faketoken");

        return response;
    }
}
