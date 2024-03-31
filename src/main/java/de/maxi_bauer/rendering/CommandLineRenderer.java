package de.maxi_bauer.rendering;

import de.maxi_bauer.board.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;

public class CommandLineRenderer implements Renderer {
    @Override
    public void renderBoard(final GamePositions gamePositions) {
        clearConsole();

        PlayerSymbol[][] positions = gamePositions.positions();

        for (int rowIdx = 0; rowIdx < positions.length; rowIdx++) {
            PlayerSymbol[] row = positions[rowIdx];

            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                PlayerSymbol cell = row[colIdx];
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
    public void message(String message) {
        System.out.println(message);
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
        } catch (Exception ignored) {
            throw new IllegalStateException("Clearing the console has gone horribly wrong!");
        }
    }
}
