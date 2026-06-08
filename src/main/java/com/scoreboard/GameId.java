package com.scoreboard;

public record GameId(String homeTeam, String awayTeam) {

    public GameId {

        homeTeam = normalize(homeTeam);
        awayTeam = normalize(awayTeam);
    }

    private String normalize(String team) {
        if (team == null) {
            throw new IllegalArgumentException("Team name cannot be null");
        }

        String stripped = team.strip();

        if (stripped.isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be blank");
        }

        return stripped;
    }

}
