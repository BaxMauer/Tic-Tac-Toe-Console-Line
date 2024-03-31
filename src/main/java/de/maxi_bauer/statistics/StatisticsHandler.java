package de.maxi_bauer.statistics;

import de.maxi_bauer.player.PlayerSymbol;

public interface StatisticsHandler {
    void registerWin(final PlayerSymbol playerSymbol);

    GameStatistics getStatistics();
}
