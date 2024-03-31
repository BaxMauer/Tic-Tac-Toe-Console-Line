package de.maxi_bauer.statistics;

import de.maxi_bauer.player.PlayerSymbol;

import java.util.ArrayList;

/**
 * Implementation of StatisticsHandler that stores game statistics in memory.
 */
public class InMemoryStatistics implements StatisticsHandler {
    private final GameStatistics statistics = new GameStatistics(new ArrayList<>());

    /**
     * Registers a win for the specified {@link PlayerSymbol}.
     *
     * @param playerSymbol The {@link PlayerSymbol} of the player who won.
     */
    @Override
    public void registerWin(final PlayerSymbol playerSymbol) {
        statistics.wins().add(new GameWin(playerSymbol));
    }

    /**
     * Retrieves the current {@link GameStatistics}.
     *
     * @return The current {@link GameStatistics].
     */
    @Override
    public GameStatistics getStatistics() {
        return statistics;
    }
}
