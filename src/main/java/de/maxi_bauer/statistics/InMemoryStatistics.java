package de.maxi_bauer.statistics;

import de.maxi_bauer.player.PlayerSymbol;

import java.util.ArrayList;

public class InMemoryStatistics implements StatisticsHandler {
    private GameStatistics statistics = new GameStatistics(new ArrayList<>());

    @Override
    public void registerWin(final PlayerSymbol playerSymbol) {
        statistics.wins().add(new GameWin(playerSymbol));
    }

    @Override
    public GameStatistics getStatistics() {
        return statistics;
    }
}
