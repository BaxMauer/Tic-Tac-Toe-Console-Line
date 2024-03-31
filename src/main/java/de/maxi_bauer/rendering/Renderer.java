package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;

public interface Renderer {
    void renderBoard(final GamePositions positions);

    void message(final String message);
}
