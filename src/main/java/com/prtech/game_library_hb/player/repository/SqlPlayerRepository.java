package com.prtech.game_library_hb.player.repository;

import com.prtech.game_library_hb.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlPlayerRepository extends PlayerRepository, JpaRepository<Player,Integer> {
}
