# Football World Cup Scoreboard Library

## Overview
Simple in-memory library for managing a live Football World Cup scoreboard.

## Features
The scoreboard supports the following operations:
1. Start a game:
    Registers a new match with initial score 0 - 0
2. Finish a game:
    Removes an existing match from the scoreboard
3. Update score:
    Updates the score of an existing match
4. Get summary:
   Returns all active games ordered by:
   - Total score (descending)
   - Most recently started (descending)

## Setup and Run
1. Clone the repository:
   ```bash
   git clone https://github.com/DawidKB/ScoreBoard.git
2. Create a jar:
   ```bash
   mvn clean package

## Notes
There was no information in the task about concurrency, but I decided to apply it. I wanted to show, that I also care about performance.
I assumed team names are considered case-insensitive.
