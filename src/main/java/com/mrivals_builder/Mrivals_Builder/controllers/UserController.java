package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.config.annotations.AdminOnly;
import com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @AdminOnly
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/role/admin")
    @AdminOnly
    public ResponseEntity<List<UserDTO>> changeUsersRoleToAdmin(@RequestBody List<Long> ids){
        return new ResponseEntity<>(userService.changeUsersRoleToAdmin(ids), HttpStatus.OK);
    }

    @PutMapping("/role/user")
    @AdminOnly
    public ResponseEntity<List<UserDTO>> changeUsersRoleToUser(@RequestBody List<Long> ids){
        return new ResponseEntity<>(userService.changeUsersRoleToUser(ids), HttpStatus.OK);
    }

    @PatchMapping("/mr-account/{id}")
    public ResponseEntity<UserDTO> changeMRAccount(Principal principal, @PathVariable Long id, @RequestBody String accountName){
        return new ResponseEntity<>(userService.changeMRAccount(principal, id, accountName), HttpStatus.OK);
    }
}
