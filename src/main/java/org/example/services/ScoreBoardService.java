package org.example.services;

import org.example.models.Match;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {
    private final List<Match> matches;
    private final Clock clock;

    static final String MATCH_ALREADY_ON_THE_BOARD = "Match is already on the score board";
    static final String MATCH_NOT_ON_THE_BOARD = "Match has to be on the score board";
    static final String MATCH_NOT_ONGOING = "The match is not ongoing";
    static final String NULL_TEAM = "team1Name and team2Name cannot be Null";

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

    public void addGoalForTeam1(String team1Name, String team2Name) {
        var match = findOngoingMatchOnTheScoreBoard(team1Name, team2Name);
        match.increaseTeam1Score();
    }

    public void addGoalForTeam2(String team1Name, String team2Name) {
        var match = findOngoingMatchOnTheScoreBoard(team1Name, team2Name);
        match.increaseTeam2Score();
    }

    public String getScoreBoard() {
        StringBuilder sb = new StringBuilder("SCOREBOARD");
        for (Match match : this.matches) {
            sb.append("\n").append(match.getScore());
        }
        return sb.toString();
    }

    protected Match findOngoingMatchOnTheScoreBoard(String team1Name, String team2Name) {
        if (team1Name == null || team2Name == null) {
            throw new IllegalArgumentException(NULL_TEAM);
        }
        for (var match : matches) {
            if (match.isSameMatch(team1Name, team2Name)) {
                validateIfOngoingMatch(match);
                return match;
            }
        }
        throw new IllegalArgumentException(MATCH_NOT_ON_THE_BOARD);
    }

    private void validateIfOngoingMatch(Match match) {
        if (!(OffsetDateTime.now(clock).isBefore(match.getStartDateTime().plusMinutes(90)))) {
            throw new IllegalArgumentException(MATCH_NOT_ONGOING);
        }
    }
}
