package com.prtech.game_library_hb;

import com.prtech.game_library_hb.team.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameLibraryHbApplicationTests {
	private TeamRepository teamRepository;


	@Test
	void contextLoads() {
		Assertions.assertFalse(teamRepository.findAll().isEmpty());
	}

}
