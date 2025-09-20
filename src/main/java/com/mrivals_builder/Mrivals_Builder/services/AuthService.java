package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.RegisterResponseDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.PasswordResetToken;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.PasswordResetTokenRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import com.mrivals_builder.Mrivals_Builder.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

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
            throw new RuntimeException("Your account has been banned. Please contact mrbuilder.contact@gmail.com.");
        else {
            String token = jwtUtils.generateToken(email, user.getRole());
            return new RegisterResponseDTO(token, new UserDTO(user));
        }
    }

    public void createPasswordResetToken(String email) {

        tokenRepository.deleteByEmail(email);

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:5173/reset-password?token=" + token;
        emailService.sendEmail(email, "Password Reset Request",
                "Click here to reset your password: " + resetLink);
    }

    public boolean resetPassword(String token, String newPassword) {
        var resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        var user = userRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);

        return true;
    }

    public UserDTO getCurrentUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserDTO(user);
    }
}
