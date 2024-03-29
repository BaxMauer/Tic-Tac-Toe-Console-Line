package de.maxi_bauer.board;

import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.player.PlayerSymbol;

import java.util.Arrays;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL_CHAR;

public record GamePositions(PlayerSymbol[][] positions) {

    public static GamePositions newPositions(final int gamePositionsSize) {
        PlayerSymbol defaultPlayerSymbol = new PlayerSymbol(BLANK_PLAYER_SYMBOL_CHAR);
        PlayerSymbol[][] positions = new PlayerSymbol[gamePositionsSize][gamePositionsSize];

        for (int i = 0; i < gamePositionsSize; i++) {
            for (int j = 0; j < gamePositionsSize; j++) {
                positions[i][j] = defaultPlayerSymbol;
            }
        }

        return new GamePositions(positions);
    }

    public void makeMove(GameMove move, PlayerSymbol playerSymbol) {
        validateMove(move);
        positions[move.row()][move.column()] = playerSymbol;
    }

    private boolean isCellAvailable(GameMove move) {
        PlayerSymbol gameCell = positions[move.row()][move.column()];
        return gameCell.symbol() == BLANK_PLAYER_SYMBOL_CHAR;
    }

    public void validateMove(GameMove move) {
        if (!validateMoveBounds(move.row()) || !validateMoveBounds(move.column())) {
            throw new IllegalArgumentException();
        }
        if (!isCellAvailable(move)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateMoveBounds(final int value) {
        return value >= 0 && value < positions.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePositions that = (GamePositions) o;
        return Arrays.deepEquals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(positions);
    }
}
