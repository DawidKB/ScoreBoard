package com.scoreboard;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {

    private static final Comparator<Game> SUMMARY_COMPARATOR =
            Comparator.comparingInt(Game::getTotalScore).reversed()
                    .thenComparing(Game::getStartTime, Comparator.reverseOrder());

    private final Map<GameId, Game> activeGames = new ConcurrentHashMap<>();

    public Game startGame(String homeTeam, String awayTeam) {
        GameId id = toId(homeTeam, awayTeam);
        Game game = new Game(id);

        if (activeGames.putIfAbsent(id, game) != null) {
            throw new IllegalStateException("Game already exists: " + id);
        }

        return game;
    }

    public void finishGame(String homeTeam, String awayTeam) {
        GameId id = toId(homeTeam, awayTeam);

        if (activeGames.remove(id) == null) {
            throw new NoSuchElementException("Game not found: " + id);
        }
    }

    public void updateScore(String homeTeam, String awayTeam,
                            int homeScore, int awayScore) {
        GameId id = toId(homeTeam, awayTeam);
        Game game = getExistingGame(id);
        game.updateScore(homeScore, awayScore);
    }

    public List<Game> getSummary() {
        return activeGames.values().stream()
                .sorted(SUMMARY_COMPARATOR)
                .toList();
    }

    private GameId toId(String home, String away) {
        return new GameId(home, away);
    }

    private Game getExistingGame(GameId id) {
        Game game = activeGames.get(id);

        if (game == null) {
            throw new NoSuchElementException("Game not found: " + id);
        }

        return game;
    }

}