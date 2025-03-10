package org.example.services;

import org.example.models.Match;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {
    private final List<Match> matches;
    private Clock clock;

    static final String MATCH_ALREADY_ON_THE_BOARD = "Match is already on the score board";

    public ScoreBoardService(Clock clock) {
        this.clock = clock;
        this.matches = new ArrayList<>();
    }

    public boolean addMatch(String team1Name, String team2Name, OffsetDateTime startDateTime) {
        var match = new Match(team1Name, team2Name, startDateTime);

        if (matches.contains(match)) {
            throw new IllegalArgumentException(MATCH_ALREADY_ON_THE_BOARD);
        }
        return this.matches.add(match);
    }
}
