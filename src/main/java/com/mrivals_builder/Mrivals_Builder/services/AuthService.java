package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.MarvelRivalsAccount;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import com.mrivals_builder.Mrivals_Builder.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public RegisterResponseDTO register(RegisterRequestDTO request){
        if (userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }
        String encryptedPassword = passwordEncoder.encode(request.password());
        User created = new User();
        created.setEmail(request.email());
        created.setUsername(request.username());
        created.setPassword(encryptedPassword);
        User saved = userRepository.save(created);

        String token = jwtUtils.generateToken(saved.getEmail(), saved.getRole());

        UserDTO userDTO = new UserDTO(saved);
        RegisterResponseDTO response = new RegisterResponseDTO(token, userDTO);

        return response;
    }

    public RegisterResponseDTO login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email or password is incorrect. Please try again."));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Email or password is incorrect. Please try again.");
        } else if (user.isBanned())
            throw new RuntimeException("Your account has been banned. Please contact admins.");
        else {
            String token = jwtUtils.generateToken(email, user.getRole());
            return new RegisterResponseDTO(token, new UserDTO(user));
        }
    }

    public UserDTO getCurrentUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserDTO(user);
    }
}
