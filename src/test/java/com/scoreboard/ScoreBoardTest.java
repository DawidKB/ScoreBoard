package com.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

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

    @Test
    void shouldNotAllowDuplicateGames() {
        ScoreBoard board = new ScoreBoard();

        var game = board.startGame("Poland", "Brazil");

        var ex = assertThrows(IllegalStateException.class,
                () -> board.startGame("Poland", "Brazil"));
        assertEquals("Game already exists: " + game.getId(), ex.getMessage());
    }

    @Test
    void shouldRemoveFinishedGame() {
        ScoreBoard board = new ScoreBoard();

        board.startGame("Poland", "Brazil");

        board.finishGame("Poland", "Brazil");

        assertTrue(board.getSummary().isEmpty());
    }

    @Test
    void shouldThrowWhenTryToRemoveGameThatNotExists() {
        ScoreBoard board = new ScoreBoard();

        var ex = assertThrows(NoSuchElementException.class,
                () -> board.finishGame("Poland", "Brazil"));
        assertTrue(ex.getMessage().contains("Game not found"));
    }

    @Test
    void shouldUpdateScore() {
        ScoreBoard board = new ScoreBoard();

        board.startGame("Poland", "Brazil");
        board.updateScore("Poland", "Brazil", 1, 2);

        Game game = board.getSummary().getFirst();
        assertEquals(1, game.getHomeScore());
        assertEquals(2, game.getAwayScore());
    }

    @Test
    void shouldThrowWhenTryToUpdateGameScoreThatNotExists() {
        ScoreBoard board = new ScoreBoard();

        var ex = assertThrows(NoSuchElementException.class,
                () -> board.updateScore("Poland", "Brazil", 0, 0));
        assertTrue(ex.getMessage().contains("Game not found"));
    }

}