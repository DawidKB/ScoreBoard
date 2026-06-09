package com.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {

    private final Map<GameId, Game> activeGames = new ConcurrentHashMap<>();

    public Game startGame(String homeTeam, String awayTeam) {
        GameId id = toId(homeTeam, awayTeam);
        Game game = new Game(id);

        if (activeGames.putIfAbsent(id, game) != null) {
            throw new IllegalStateException("Game already exists: " + id);
        }

        return game;
    }

    public List<Game> getSummary() {
        return new ArrayList<>(activeGames.values());
    }

    private GameId toId(String home, String away) {
        return new GameId(home, away);
    }
}