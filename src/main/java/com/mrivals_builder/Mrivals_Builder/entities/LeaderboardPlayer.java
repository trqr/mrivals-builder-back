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
    private int seasonWinCount;

    @ManyToOne
    private Hero hero;

    private int heroMatches;
    private int heroWins;
    private int heroKills;
    private int heroDeaths;
    private int heroAssists;
    private int heroMvps;
    private int heroSvps;

    public LeaderboardPlayer(String name, String icon, double rankScore, double maxRankScore, int seasonWinCount, Hero hero, int heroMatches, int heroWins, int heroKills, int heroDeaths, int heroAssists, int heroMvps, int heroSvps) {
        this.name = name;
        this.icon = icon;
        this.rankScore = rankScore;
        this.maxRankScore = maxRankScore;
        this.seasonWinCount = seasonWinCount;
        this.hero = hero;
        this.heroMatches = heroMatches;
        this.heroWins = heroWins;
        this.heroKills = heroKills;
        this.heroDeaths = heroDeaths;
        this.heroAssists = heroAssists;
        this.heroMvps = heroMvps;
        this.heroSvps = heroSvps;
    }
}
