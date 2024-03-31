package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.statistics.GameStatistics;

public interface Renderer {
    void renderBoard(final GamePositions positions);

    void message(final String message);

    void renderStatistics(final GameStatistics statistics);
}
