package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;

import java.util.Optional;

/**
 * Interface for representing a player in the game.
 */
public interface Player {

    /**
     * Retrieves a {@link GameMove} made by the player.
     *
     * @return An optional {@link GameMove} made by the player.
     */
    Optional<GameMove> getMove();

    /**
     * Retrieves the {@link PlayerSymbol} associated with the player.
     *
     * @return The {@link PlayerSymbol} associated with the player.
     */
    PlayerSymbol getSymbol();
}
