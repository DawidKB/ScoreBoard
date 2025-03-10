package org.example.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.example.services.ScoreBoardService.*;
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

    @Test
    void shouldThrowExceptionWhenTryToUpdateScoreAndTeam1IsNull() {
        var err = assertThrows(IllegalArgumentException.class, () -> scoreService.findOngoingMatchOnTheScoreBoard(null, TEAM2_NAME));
        assertEquals(NULL_TEAM, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTryToUpdateScoreAndTeam2IsNull() {
        var err = assertThrows(IllegalArgumentException.class, () -> scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, null));
        assertEquals(NULL_TEAM, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTryToUpdateScoreAndMatchIsNotOnTheBoard() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);

        var err = assertThrows(IllegalArgumentException.class, () -> scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM3_NAME));
        assertEquals(MATCH_NOT_ON_THE_BOARD, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTryToUpdateScoreOfFinishedMatch() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now.minusMinutes(91));

        var err = assertThrows(IllegalArgumentException.class, () -> scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM2_NAME));
        assertEquals(MATCH_NOT_ONGOING, err.getMessage());
    }

    @Test
    void shouldFindOngoingMatchSuccessfully() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);

        var match = scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM2_NAME);

        assertNotNull(match);
    }

    @Test
    void shouldTeam1ScoreAGoalSuccessfullyWhenMatchIsOngoing() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);

        scoreService.addGoalForTeam1(TEAM1_NAME, TEAM2_NAME);

        var match = scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM2_NAME);
        assertEquals(1, match.getTeam1Score());
        assertEquals(0, match.getTeam2Score());
    }

    @Test
    void shouldTeam2ScoreAGoalSuccessfullyWhenMatchIsOngoing() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);

        scoreService.addGoalForTeam2(TEAM1_NAME, TEAM2_NAME);

        var match = scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM2_NAME);
        assertEquals(0, match.getTeam1Score());
        assertEquals(1, match.getTeam2Score());
    }

    @Test
    void shouldGetScoreboardSuccessfully() {
        scoreService.addMatch(TEAM1_NAME, TEAM2_NAME, now);
        var match = scoreService.findOngoingMatchOnTheScoreBoard(TEAM1_NAME, TEAM2_NAME);

        var scoreBoard = scoreService.getScoreBoard();

        assertTrue(scoreBoard.contains(match.getScore()));
    }
}