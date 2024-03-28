package de.maxi_bauer.rendering;

import de.maxi_bauer.data.GamePositions;

public class CommandLineBoardRenderer implements BoardRenderer {
    @Override
    public void renderBoard(final GamePositions gamePositions) {
        char[][] positions = gamePositions.positions();

        for (int rowIdx = 0; rowIdx < positions.length; rowIdx++) {
            char[] row = positions[rowIdx];

            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                char cell = row[colIdx];
                System.out.printf(" %c ", cell);
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
}
