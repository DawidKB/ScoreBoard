package com.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void shouldStartGame() {
        ScoreBoard board = new ScoreBoard();

        Game game = board.startGame("Poland", "Brazil");

        assertNotNull(game);
    }

    @Test
    void shouldKeepStartedGameInSummary() {
        ScoreBoard board = new ScoreBoard();

        board.startGame("Poland", "Brazil");

        assertEquals(1, board.getSummary().size());
    }
}