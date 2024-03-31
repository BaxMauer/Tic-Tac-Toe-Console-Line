package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.statistics.GameStatistics;

/**
 * Interface for rendering game components in different output formats.
 */
public interface Renderer {

    /**
     * Renders the game board.
     *
     * @param positions The positions of the game board.
     */
    void renderBoard(final GamePositions positions);

    /**
     * Displays a message.
     *
     * @param message The message to be displayed.
     */
    void message(final String message);

    /**
     * Renders game statistics.
     *
     * @param statistics The statistics to be rendered.
     */
    void renderStatistics(final GameStatistics statistics);
}
