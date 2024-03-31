package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.statistics.GameStatistics;
import de.maxi_bauer.statistics.GameWin;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * A renderer implementation for rendering game components in the command line interface.
 */
public class CommandLineRenderer implements Renderer {

    /**
     * Renders the game board in the command line interface.
     *
     * @param gamePositions The {@link GamePositions} of the game board.
     */
    @Override
    public void renderBoard(final GamePositions gamePositions) {
        clearConsole();

        final PlayerSymbol[][] positions = gamePositions.positions();

        // Iterate over each row of the game board
        for (int rowIdx = 0; rowIdx < positions.length; rowIdx++) {
            final PlayerSymbol[] row = positions[rowIdx];

            // Iterate over each column of the current row
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                final PlayerSymbol cell = row[colIdx];
                System.out.printf(" %c ", cell.symbol());
                if (colIdx != row.length - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
            if (rowIdx != positions.length - 1) {
                for (int colIdx = 0; colIdx < row.length; colIdx++) {
                    System.out.print("----");
                }
                System.out.println();
            }
        }
    }

    /**
     * Displays a message in the command line interface.
     *
     * @param message The message to be displayed.
     */
    @Override
    public void message(final String message) {
        System.out.println(message);
    }

    /**
     * Renders {@link GameStatistics} in the command line interface.
     *
     * @param statistics The {@link GameStatistics} to be rendered.
     */
    @Override
    public void renderStatistics(final GameStatistics statistics) {

        final Map<PlayerSymbol, Long> wins = statistics
                .wins()
                .stream()
                .collect(Collectors.groupingBy(GameWin::winningPlayerSymbol, Collectors.counting()));

        final StringBuilder winsString = new StringBuilder();

        if (wins.isEmpty()) {
            winsString.append("No wins yet");
        } else {
            for (final Map.Entry<PlayerSymbol, Long> playerWins : wins.entrySet()) {
                winsString
                        .append(playerWins.getKey().symbol())
                        .append(" wins: ")
                        .append(playerWins.getValue())
                        .append(System.lineSeparator());
            }
        }

        final String message = """
                Stats:
                %s
                Press enter to continue....
                """;

        clearConsole();
        System.out.printf(message, winsString);
    }

    /**
     * Clears the console screen.
     */
    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // For Windows operating systems
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like operating systems
                System.out.print("\033[H\033[2J");
            }
        } catch (final Exception ignored) {
            throw new IllegalStateException("Clearing the console has gone horribly wrong!");
        }
    }
}
