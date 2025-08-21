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
public class Hero {

    @Id
    private Long id;
    private String name;
    private String imageLink;
    private String role;
    private String attackType;
    private Long difficulty;
    private String bio;
    private String lore;
    private double winRate;

    @OneToMany(mappedBy = "hero")
    private List<Ability> abilities;

    @OneToMany(mappedBy = "hero")
    private List<Synergie> synergies;

    @OneToMany(mappedBy = "hero")
    private List<MatchUp> matchUps;

    @ManyToMany(mappedBy = "heroes")
    private List<Compo> compos;
}
