package com.mrivals_builder.Mrivals_Builder.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role = "USER";
    private boolean isBanned = false;

    @OneToMany(mappedBy = "user")
    private List<Team> teams;

    @OneToMany(mappedBy = "user")
    private List<MarvelRivalsAccount> playerStats;

}
