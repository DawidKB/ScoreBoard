package org.example.models;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.example.models.Match.NULL_TEAM;
import static org.example.models.Match.SAME_TEAM;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MatchTest {
    private Match match;
    private static final Clock CLOCK = Clock.fixed(Instant.parse("1939-09-01T00:00:01.00Z"), ZoneId.of("UTC"));
    private static final String TEAM1_NAME = "team1 name";
    private static final String TEAM2_NAME = "team2 name";

    private final OffsetDateTime now = OffsetDateTime.now(CLOCK);

    @Test
    void shouldThrowExceptionWhenTeam1IsNull() {
        var err = assertThrows(IllegalArgumentException.class, () -> new Match(null, TEAM2_NAME, now));
        assertEquals(NULL_TEAM, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTeam2IsNull() {
        var err = assertThrows(IllegalArgumentException.class, () -> new Match(TEAM1_NAME, null, now));
        assertEquals(NULL_TEAM, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBothTeamsAreNull() {
        var err = assertThrows(IllegalArgumentException.class, () -> new Match(null, null, now));
        assertEquals(NULL_TEAM, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTeamsAreTheSame() {
        var err = assertThrows(IllegalArgumentException.class, () -> new Match(TEAM1_NAME, TEAM1_NAME, now));
        assertEquals(SAME_TEAM, err.getMessage());
    }

    @Test
    void shouldIncrementTeam1Score() {
        match = new Match(TEAM1_NAME, TEAM2_NAME, OffsetDateTime.now(CLOCK));

        match.increaseTeam1Score();

        assertEquals(1, match.getTeam1Score());
        assertEquals(0, match.getTeam2Score());
    }

    @Test
    void shouldIncrementTeam2Score() {
        match = new Match(TEAM1_NAME, TEAM2_NAME, OffsetDateTime.now(CLOCK));

        match.increaseTeam2Score();

        assertEquals(0, match.getTeam1Score());
        assertEquals(1, match.getTeam2Score());
    }

    @Test
    void shouldReturnTrueIfSameMatch() {
        match = new Match(TEAM1_NAME, TEAM2_NAME, OffsetDateTime.now(CLOCK));

        assertTrue(match.isSameMatch(TEAM1_NAME, TEAM2_NAME));
        assertTrue(match.isSameMatch(TEAM2_NAME, TEAM1_NAME));
    }

    @Test
    void shouldReturnFalseIfDifferentMatch() {
        final var dummyName = "dummyName";
        match = new Match(TEAM1_NAME, TEAM2_NAME, OffsetDateTime.now(CLOCK));

        assertFalse(match.isSameMatch(TEAM1_NAME, dummyName));
        assertFalse(match.isSameMatch(dummyName, TEAM1_NAME));
    }
}