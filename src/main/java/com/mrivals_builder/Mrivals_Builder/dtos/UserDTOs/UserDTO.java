package com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs;

import com.mrivals_builder.Mrivals_Builder.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String mrivalsAccount;
    private String role;
    private boolean banned;


    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.mrivalsAccount = user.getMrivalsAccount();
        this.role = user.getRole();
        this.banned = user.isBanned();
    }
}
