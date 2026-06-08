package com.scoreboard;

import org.junit.jupiter.api.Test;

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
}