package de.maxi_bauer.command;

import de.maxi_bauer.board.GameMove;

import java.util.Optional;

public interface CommandParser {
    Optional<GameMove> getMove();

    GameCommand getGameRestart();
}
