package com.scoreboard;

import java.time.Clock;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class Game {

    private static final Score INITIAL_SCORE = new Score(0, 0);

    private final GameId id;
    private final Instant startTime;
    private final Score score;

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

    public int getHomeScore() {
        return score.home();
    }

    public int getAwayScore() {
        return score.away();
    }

    public record Score(int home, int away) {}

}
