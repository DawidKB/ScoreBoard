package com.scoreboard;

public record GameId(String homeTeam, String awayTeam) {

    public GameId {

        homeTeam = validate(homeTeam);
        awayTeam = validate(awayTeam);
    }

    private String validate(String team) {
        if (team == null) {
            throw new IllegalArgumentException("Team name cannot be null");
        }

        return team;
    }

}
