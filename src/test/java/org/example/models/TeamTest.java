package org.example.models;

import org.junit.jupiter.api.Test;

import static org.example.models.Team.MISSING_TEAM_NAME;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void shouldThrowExceptionWhenCreateTeamWithNullName() {
        var err  = assertThrows(IllegalArgumentException.class, () -> new Team(null));
        assertEquals(MISSING_TEAM_NAME, err.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreateTeamWithEmptyName() {
        var err  = assertThrows(IllegalArgumentException.class, () -> new Team(""));
        assertEquals(MISSING_TEAM_NAME, err.getMessage());
    }

    @Test
    void shouldCreateTeamAndGetNameSuccessfully() {
        final var TEAM_NAME = "teamName";
        Team team = new Team(TEAM_NAME);

        assertEquals(TEAM_NAME, team.name());
    }
}