package com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs;

import com.mrivals_builder.Mrivals_Builder.dtos.MarvelRivalsAccountDTOs.MarvelRivalsAccountDTO;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private List<MarvelRivalsAccountDTO> accounts;
    private String role;
    private boolean banned;


    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.accounts = user.getPlayerStats() != null
                ? user.getPlayerStats().stream()
                .map(MarvelRivalsAccountDTO::new)
                .toList()
                : List.of();
        this.role = user.getRole();
        this.banned = user.isBanned();
    }
}
