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

    @Test
    void shouldStartWithZeroScores() {
        Game game = new Game(new GameId("Poland", "Brazil"));

        assertEquals(0, game.getHomeScore());
        assertEquals(0, game.getAwayScore());
    }

    @Test
    void shouldUpdateScore() {
        Game game = new Game(new GameId("Poland", "Brazil"));

        game.updateScore(2, 3);

        assertEquals(2, game.getHomeScore());
        assertEquals(3, game.getAwayScore());
    }

    @Test
    void shouldThrowWhenNegativeScore() {
        Game game = new Game(new GameId("Poland", "Brazil"));

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> game.updateScore(-1, 0));

        assertEquals("Score cannot be negative", ex.getMessage());
    }

    @Test
    void shouldReturnTotalScore() {
        Game game = new Game(new GameId("Poland", "Brazil"));

        game.updateScore(2, 3);

        assertEquals(5, game.getTotalScore());
    }

    @Test
    void shouldReturnTeamsFromId() {
        Game game = new Game(new GameId("Poland", "Brazil"));

        assertEquals("poland", game.getHomeTeam());
        assertEquals("brazil", game.getAwayTeam());
    }

    @Test
    void shouldBeEqualIfSameGameId() {
        GameId id = new GameId("Poland", "Brazil");

        Game g1 = new Game(id);
        Game g2 = new Game(id);

        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
    }

}