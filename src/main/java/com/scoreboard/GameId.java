package com.scoreboard;

import java.util.Locale;

public record GameId(String homeTeam, String awayTeam) {

    public GameId {
        homeTeam = normalize(homeTeam);
        awayTeam = normalize(awayTeam);

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Teams must be different");
        }
    }

    private String normalize(String team) {
        if (team == null) {
            throw new IllegalArgumentException("Team name cannot be null");
        }

        String stripped = team.strip();

        if (stripped.isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be blank");
        }

        return stripped
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }

}
