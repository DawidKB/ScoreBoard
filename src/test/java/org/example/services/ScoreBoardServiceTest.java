package org.example.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.example.services.ScoreBoardService.MATCH_ALREADY_ON_THE_BOARD;
import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardServiceTest {
    private static final Clock CLOCK = Clock.fixed(Instant.parse("1939-09-01T00:00:01.00Z"), ZoneId.of("UTC"));
    private static final String TEAM1_NAME = "team1 name";
    private static final String TEAM2_NAME = "team2 name";
    private static final String TEAM3_NAME = "team3 name";

    private final OffsetDateTime now = OffsetDateTime.now(CLOCK);
    private ScoreBoardService scoreService;

    @BeforeEach
    void setUp() {
        scoreService = new ScoreBoardService(CLOCK);
    }

    @Test
    void shouldAddMatchSuccessfully() {
        assertTrue(scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now));
    }

    @Test
    void shouldAddMatchSuccessfullyWithAllVariationsOf3Teams() {
        assertTrue(scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now));
        assertTrue(scoreService.addMatch(TEAM2_NAME, TEAM3_NAME, now));
        assertTrue(scoreService.addMatch(TEAM1_NAME, TEAM3_NAME, now));
    }

    @Test
    void shouldThrowExceptionWhenMatchAlreadyOnTheScoreBoard() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);
        var err = assertThrows(IllegalArgumentException.class, () -> scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now));
        assertEquals(MATCH_ALREADY_ON_THE_BOARD, err.getMessage());
    }
}