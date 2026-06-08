package com.scoreboard;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldCreateGameWithId() {
        GameId id = new GameId("Poland", "Brazil");

        Game game = new Game(id);

        assertEquals(id, game.getId());
    }

    @Test
    void shouldThrowWhenIdNull() {
        var ex = assertThrows(NullPointerException.class,
                () -> new Game(null));
        assertEquals("id must not be null", ex.getMessage());
    }

    @Test
    void shouldSetStartTimeUsingClock() {
        Instant fixedInstant = Instant.parse("2024-01-01T10:00:00Z");
        Clock clock = Clock.fixed(fixedInstant, ZoneOffset.UTC);

        Game game = new Game(new GameId("Poland", "Brazil"), clock);

        assertEquals(fixedInstant, game.getStartTime());
    }

    @Test
    void shouldThrowWhenClockIsNull() {
        var  ex = assertThrows(NullPointerException.class,
                () -> new Game(new GameId("Poland", "Brazil"), null));
        assertEquals("clock must not be null", ex.getMessage());
    }

}