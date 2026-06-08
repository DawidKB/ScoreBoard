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

}