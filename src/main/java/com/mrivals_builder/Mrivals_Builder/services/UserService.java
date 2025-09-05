package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
