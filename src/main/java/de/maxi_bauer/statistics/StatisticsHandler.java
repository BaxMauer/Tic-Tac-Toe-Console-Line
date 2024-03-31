package de.maxi_bauer.statistics;

import de.maxi_bauer.player.PlayerSymbol;

/**
 * Interface for handling game statistics.
 */
public interface StatisticsHandler {

    /**
     * Registers a win for the specified player symbol.
     *
     * @param playerSymbol The symbol of the player who won.
     */
    void registerWin(final PlayerSymbol playerSymbol);

    /**
     * Retrieves the current game statistics.
     *
     * @return The current game statistics.
     */
    GameStatistics getStatistics();
}
