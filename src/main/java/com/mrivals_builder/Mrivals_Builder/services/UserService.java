package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.NewPasswordRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDTO> dtos = users.stream().map(user -> new UserDTO(user)).toList();

        return dtos;
    }

    public List<UserDTO> changeUsersRoleToAdmin(List<Long> ids) {
        List<User> users = userRepository.findAllById(ids);

        users.forEach(user -> user.setRole("ADMIN"));
        userRepository.saveAll(users);

        return users.stream().map(user -> new UserDTO(user)).toList();
    }

    public List<UserDTO> changeUsersRoleToUser(List<Long> ids) {
        List<User> users = userRepository.findAllById(ids);

        users.forEach(user -> user.setRole("USER"));
        userRepository.saveAll(users);

        return users.stream().map(user -> new UserDTO(user)).toList();
    }

    public UserDTO changeMRAccount(Principal principal, Long id, String accountName) {
        User currentUser = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not Found!"));

        if (!currentUser.getId().equals(id))
            throw new BadRequestException("Request not allowed");

        currentUser.setMrivalsAccount(accountName);
        userRepository.save(currentUser);
        return new UserDTO(currentUser);
    }

    public UserDTO changePassword(Principal principal, NewPasswordRequestDTO requestDTO) {
        User currentUser = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not Found!"));

        if (passwordEncoder.matches(requestDTO.oldPassword(), currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(requestDTO.newPassword()));
            userRepository.save(currentUser);
            return new UserDTO(currentUser);
        }

        throw new BadRequestException("You current password is not correct");
    }

    public UserDTO changeUsername(Principal principal, String userName) {
        User currentUser = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not Found!"));

        currentUser.setUsername(userName);
        userRepository.save(currentUser);
        return new UserDTO(currentUser);
    }
}
