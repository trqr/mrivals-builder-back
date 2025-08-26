package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import com.mrivals_builder.Mrivals_Builder.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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

        String token = jwtUtil.generateToken(saved.getEmail(), saved.getRole());

        UserDTO userDTO = new UserDTO(saved);
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setUser(userDTO);
        response.setToken(token);

        return response;
    }

    public RegisterResponseDTO login(String email, String password){
        if (!userRepository.existsByEmail(email)){
            throw new NotFoundException("Email or password is incorrect. Please try again.");
        }

        User user = userRepository.findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Email or password is incorrect. Please try again.");
        } else {
            String token = jwtUtil.generateToken(email, user.getRole());
            return new RegisterResponseDTO(token, new UserDTO(user));
        }
    }
}
