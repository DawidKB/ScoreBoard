package com.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameIdTest {

    @Test
    void shouldCreateGameId() {
        GameId id = new GameId("Poland", "Brazil");

        assertEquals("Poland", id.homeTeam());
        assertEquals("Brazil", id.awayTeam());
    }

}