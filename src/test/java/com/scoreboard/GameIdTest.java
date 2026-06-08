package com.scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GameIdTest {

    @Test
    void shouldCreateGameId() {
        GameId id = new GameId("Poland", "Brazil");

        assertEquals("Poland", id.homeTeam());
        assertEquals("Brazil", id.awayTeam());
    }

    @Test
    void shouldThrowWhenHomeTeamIsNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new GameId(null, "Brazil"));
        assertEquals("Team name cannot be null", ex.getMessage());
    }

    @Test
    void shouldThrowWhenAwayTeamIsNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> new GameId("Poland", null));
        assertEquals("Team name cannot be null", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    void shouldThrowWhenHomeTeamIsBlank(String team) {
        var ex = assertThrows(IllegalArgumentException.class, () -> new GameId(team, "Brazil"));
        assertEquals("Team name cannot be blank", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    void shouldThrowWhenAwayTeamIsBlank(String team) {
        var ex = assertThrows(IllegalArgumentException.class,
                            () -> new GameId("Poland", team));
        assertEquals("Team name cannot be blank", ex.getMessage());
    }

    @Test
    void shouldNormalizeWhiteSpaceAndCase() {
        GameId id = new GameId("  Real    Madrid", " MANCHESTER   CITY   ");

        assertEquals("real madrid", id.homeTeam());
        assertEquals("manchester city", id.awayTeam());
    }
}