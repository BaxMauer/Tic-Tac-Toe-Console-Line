package de.maxi_bauer.command;

import de.maxi_bauer.board.GameMove;

import java.util.Optional;

/**
 * Interface for parsing commands for the game.
 */
public interface CommandParser {

    /**
     * Retrieves a move entered by the user.
     *
     * @return An optional {@link GameMove} entered by the user.
     */
    Optional<GameMove> getMove();

    /**
     * Retrieves the command for restarting the game.
     *
     * @return The {@link GameCommand} for restarting the game.
     */
    GameCommand getGameRestart();
}
