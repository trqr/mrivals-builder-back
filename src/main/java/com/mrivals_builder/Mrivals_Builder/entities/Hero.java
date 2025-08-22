package com.mrivals_builder.Mrivals_Builder.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private String name;
    private String imageLink;
    private String role;
    private String attackType;
    private String difficulty;
    @Column(columnDefinition = "TEXT")
    private String bio;
    @Column(columnDefinition = "TEXT")
    private String lore;
    private double winRate;

    @OneToMany(mappedBy = "hero")
    @JsonBackReference
    private List<Ability> abilities;

    @OneToMany(mappedBy = "hero")
    private List<Synergie> synergies;

    @OneToMany(mappedBy = "hero")
    private List<MatchUp> matchUps;

    @ManyToMany(mappedBy = "heroes")
    private List<Compo> compos;
}
