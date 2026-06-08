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

    @Test
    void shouldThrowWhenAnyTeamIsNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new GameId(null, "Brazil")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new GameId("Poland", null))
        );
    }

}