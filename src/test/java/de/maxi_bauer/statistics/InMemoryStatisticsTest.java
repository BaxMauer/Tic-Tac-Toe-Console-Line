package de.maxi_bauer.statistics;

import de.maxi_bauer.player.PlayerSymbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryStatisticsTest {

    private InMemoryStatistics statisticsHandler;

    @BeforeEach
    public void setUp() {
        statisticsHandler = new InMemoryStatistics();
    }

    @Test
    public void testRegisterWin() {
        final PlayerSymbol playerSymbol = new PlayerSymbol('X');
        statisticsHandler.registerWin(playerSymbol);

        final GameStatistics gameStatistics = statisticsHandler.getStatistics();
        final List<GameWin> wins = gameStatistics.wins();

        assertEquals(1, wins.size());
        assertTrue(wins.contains(new GameWin(playerSymbol)));
    }

    @Test
    public void testGetStatistics() {
        final GameStatistics gameStatistics = statisticsHandler.getStatistics();
        final List<GameWin> wins = gameStatistics.wins();

        assertEquals(0, wins.size());
    }

    @Test
    public void testRegisterMultipleWins() {
        final PlayerSymbol playerSymbol1 = new PlayerSymbol('X');
        final PlayerSymbol playerSymbol2 = new PlayerSymbol('O');

        statisticsHandler.registerWin(playerSymbol1);
        statisticsHandler.registerWin(playerSymbol2);
        statisticsHandler.registerWin(playerSymbol1);

        final GameStatistics gameStatistics = statisticsHandler.getStatistics();
        final List<GameWin> wins = gameStatistics.wins();

        assertEquals(3, wins.size());
        assertTrue(wins.contains(new GameWin(playerSymbol1)));
        assertTrue(wins.contains(new GameWin(playerSymbol2)));
    }
}
