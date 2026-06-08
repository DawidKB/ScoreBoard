package com.scoreboard;

import java.time.Clock;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class Game {

    private final GameId id;
    private final Instant startTime;

    public Game(GameId id, Clock clock) {
        this.id = requireNonNull(id, "id must not be null");
        this.startTime = requireNonNull(clock, "clock must not be null").instant();
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

}
