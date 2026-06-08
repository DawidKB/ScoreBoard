package com.scoreboard;

public record GameId(String homeTeam, String awayTeam) {

    public GameId {
        homeTeam = homeTeam;
        awayTeam = awayTeam;
    }

}
