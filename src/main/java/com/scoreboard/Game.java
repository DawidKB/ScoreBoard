package com.scoreboard;

import java.time.Clock;

import static java.util.Objects.requireNonNull;

public class Game {

    private final GameId id;

    public Game(GameId id) {
        this.id = requireNonNull(id, "id must not be null");
    }

    public GameId getId() {
        return id;
    }

}
