package com.scoreboard;

import java.time.Clock;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class Game {

    private static final Score INITIAL_SCORE = new Score(0, 0);

    private final GameId id;
    private final Instant startTime;
    private Score score;

    public Game(GameId id, Clock clock) {
        this.id = requireNonNull(id, "id must not be null");
        this.startTime = requireNonNull(clock, "clock must not be null").instant();
        this.score = INITIAL_SCORE;
    }

    public Game(GameId id) {
        this(id, Clock.systemUTC());
    }

    public GameId getId() {
        return id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public String getHomeTeam() {
        return id.homeTeam();
    }

    public String getAwayTeam() {
        return id.awayTeam();
    }

    public int getHomeScore() {
        return score.home();
    }

    public int getAwayScore() {
        return score.away();
    }

    public void updateScore(int homeScore, int awayScore) {
        this.score = new Score(homeScore, awayScore);
    }

    public int getTotalScore() {
        return score.total();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        Score s = score;
        return id.homeTeam() + " " + s.home()
                + " - "
                + id.awayTeam() + " " + s.away();
    }

    public record Score(int home, int away) {

        public Score {
            if (home < 0 || away < 0) {
                throw new IllegalArgumentException("Score cannot be negative");
            }
        }

        public int total() {
            return home + away;
        }

    }

}
