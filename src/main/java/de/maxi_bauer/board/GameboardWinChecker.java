package de.maxi_bauer.board;

import de.maxi_bauer.data.GamePositions;

public interface GameboardWinChecker {
    boolean isGameWon(final GamePositions positions);
}
