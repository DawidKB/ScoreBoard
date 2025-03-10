package org.example.models;

record Team(String name) {
    static final String MISSING_TEAM_NAME = "Team name cannot be null or empty";

    Team {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(MISSING_TEAM_NAME);
        }
    }
}
