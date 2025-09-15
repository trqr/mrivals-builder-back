package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.config.annotations.AdminOnly;
import com.mrivals_builder.Mrivals_Builder.dtos.MarvelRivalsAccountDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.NewPasswordRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.UserDTO;
import com.mrivals_builder.Mrivals_Builder.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @PutMapping("/ban")
    @AdminOnly
    public ResponseEntity<List<UserDTO>> banUsers(@RequestBody List<Long> ids){
        return new ResponseEntity<>(userService.banUsers(ids), HttpStatus.OK);
    }

    @PatchMapping("/mr-account/{accountId}")
    public ResponseEntity<MarvelRivalsAccountDTO> changeMRAccount(@PathVariable Long accountId, @RequestParam String accountName){
        return new ResponseEntity<>(userService.changeMRAccount(accountId, accountName), HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<UserDTO> changePassword(@Valid @RequestBody NewPasswordRequestDTO requestDTO){
        return new ResponseEntity<>(userService.changePassword(requestDTO), HttpStatus.OK);
    }

    @PatchMapping("/username")
    public ResponseEntity<UserDTO> changerUsername(@RequestParam @NotBlank String userName){
        return new ResponseEntity<>(userService.changeUsername(userName), HttpStatus.OK);
    }
}
