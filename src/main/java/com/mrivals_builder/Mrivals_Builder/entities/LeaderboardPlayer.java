package com.mrivals_builder.Mrivals_Builder.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;

    private double rankScore;
    private double maxRankScore;
    private double seasonWinCount;

    @ManyToOne
    private Hero hero;

    private int heroMatches;
    private int heroWins;
    private int heroKills;
    private int heroDeaths;
    private int heroAssists;
    private int heroMvps;
    private int heroSvps;
}
