package org.example.models;

import java.time.OffsetDateTime;

public class Match {
    private final Team team1;
    private final Team team2;
    private final OffsetDateTime startDateTime;
    private int team1Score;
    private int team2Score;

    static final String NULL_TEAM = "team1Name and team2Name cannot be Null";
    static final String SAME_TEAM = "team1 and team2 cannot be the same";

    public Match(String team1Name, String team2Name, OffsetDateTime startDateTime) {
        if (team1Name == null || team2Name == null) {
            throw new IllegalArgumentException(NULL_TEAM);
        } else if (team1Name.equals(team2Name)) {
            throw new IllegalArgumentException(SAME_TEAM);
        }
        this.team1 = new Team(team1Name);
        this.team2 = new Team(team2Name);
        this.startDateTime = startDateTime;
        this.team1Score = 0;
        this.team2Score = 0;
    }
}
