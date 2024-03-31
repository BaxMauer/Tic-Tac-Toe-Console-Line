package de.maxi_bauer.board;

/**
 * Interface for checking if the game is won based on the current {@link GamePositions} on the game board.
 */
public interface GameboardWinChecker {

    /**
     * Checks if the game is won based on the current positions on the game board.
     *
     * @param positions The {@link GamePositions} on the game board.
     * @return True if the game is won, otherwise false.
     */
    boolean isGameWon(final GamePositions positions);
}
