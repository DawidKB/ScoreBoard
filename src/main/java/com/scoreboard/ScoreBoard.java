package com.scoreboard;

public class ScoreBoard {

    public Game startGame(String homeTeam, String awayTeam) {
        GameId id = toId(homeTeam, awayTeam);

        return new Game(id);
    }

    private GameId toId(String home, String away) {
        return new GameId(home, away);
    }
}