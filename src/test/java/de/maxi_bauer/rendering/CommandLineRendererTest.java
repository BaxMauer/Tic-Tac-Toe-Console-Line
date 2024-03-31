package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.statistics.GameStatistics;
import de.maxi_bauer.statistics.GameWin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineRendererTest {

    /***
     * Test could fail when building of windows because of clear console command!
     */
    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    public void testRenderBoard() {
        // Arrange
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final CommandLineRenderer renderer = new CommandLineRenderer();
        final GamePositions gamePositions = new GamePositions(new PlayerSymbol[][]{
                {new PlayerSymbol('X'), new PlayerSymbol('O'), new PlayerSymbol('X')},
                {new PlayerSymbol('O'), new PlayerSymbol('X'), new PlayerSymbol('O')},
                {new PlayerSymbol('X'), new PlayerSymbol('O'), new PlayerSymbol('X')}
        });

        // Act
        renderer.renderBoard(gamePositions);

        // Assert
        final String expectedOutput = """
                \u001B[H\u001B[2J X | O | X\s
                ------------
                 O | X | O\s
                ------------
                 X | O | X\s
                """;
        assertEquals(expectedOutput, outContent.toString());
    }

    /***
     * Test could fail when building of windows because of clear console command!
     */
    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    public void testRenderStatistics() {
        // Arrange
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final CommandLineRenderer renderer = new CommandLineRenderer();
        final List<GameWin> wins = new ArrayList<>();
        wins.add(new GameWin(new PlayerSymbol('X')));
        wins.add(new GameWin(new PlayerSymbol('O')));
        wins.add(new GameWin(new PlayerSymbol('X')));
        final GameStatistics statistics = new GameStatistics(wins);

        // Act
        renderer.renderStatistics(statistics);

        // Assert
        final String expectedOutput = """
                \u001B[H\u001B[2JStats:
                X wins: 2
                O wins: 1

                Press enter to continue....
                """;
        assertEquals(expectedOutput, outContent.toString());
    }
}
