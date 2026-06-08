package com.scoreboard;

import java.time.Clock;

public class Game {

    private final GameId id;

    public Game(GameId id) {
        this.id = id;
    }

    public GameId getId() {
        return id;
    }

}
