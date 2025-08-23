package com.mrivals_builder.Mrivals_Builder.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private String name;
    private String type;
    @JsonProperty("isCollab")
    private boolean collab;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "hero_id")
    @JsonManagedReference
    private Hero hero;
}
