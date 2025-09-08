package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.LeaderboardPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderboardPlayerRepository extends JpaRepository<LeaderboardPlayer, Long> {
    List<LeaderboardPlayer> findByHero(Hero hero);
}
