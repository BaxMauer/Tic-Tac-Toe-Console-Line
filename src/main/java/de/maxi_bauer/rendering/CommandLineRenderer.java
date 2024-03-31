package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.statistics.GameStatistics;
import de.maxi_bauer.statistics.GameWin;

import java.util.Map;
import java.util.stream.Collectors;

public class CommandLineRenderer implements Renderer {
    @Override
    public void renderBoard(final GamePositions gamePositions) {
        clearConsole();

        final PlayerSymbol[][] positions = gamePositions.positions();

        for (int rowIdx = 0; rowIdx < positions.length; rowIdx++) {
            final PlayerSymbol[] row = positions[rowIdx];

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

    @Override
    public void message(final String message) {
        System.out.println(message);
    }

    @Override
    public void renderStatistics(GameStatistics statistics) {

        Map<PlayerSymbol, Long> wins = statistics
                .wins()
                .stream()
                .collect(Collectors.groupingBy(GameWin::winningPlayerSymbol, Collectors.counting()));

        final StringBuilder winsString = new StringBuilder();

        if (wins.isEmpty()) {
            winsString.append("No games played yet");
        } else {
            for (final Map.Entry<PlayerSymbol, Long> playerWins : wins.entrySet()) {
                winsString
                        .append(playerWins.getKey().symbol())
                        .append("wins: ")
                        .append(playerWins.getValue())
                        .append(System.lineSeparator());
            }
        }

        final String message = """
                Stats:
                %s
                Press enter to remain....
                """;

        clearConsole();
        System.out.printf(message, winsString);
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
        } catch (final Exception ignored) {
            throw new IllegalStateException("Clearing the console has gone horribly wrong!");
        }
    }
}
