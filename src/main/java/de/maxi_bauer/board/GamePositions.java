package de.maxi_bauer.board;

import de.maxi_bauer.player.PlayerSymbol;

import java.util.Arrays;
import java.util.Optional;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL_CHAR;

public record GamePositions(PlayerSymbol[][] positions) {

    public static GamePositions newPositions(final int gamePositionsSize) {
        final PlayerSymbol defaultPlayerSymbol = new PlayerSymbol(BLANK_PLAYER_SYMBOL_CHAR);
        final PlayerSymbol[][] positions = new PlayerSymbol[gamePositionsSize][gamePositionsSize];

        for (int i = 0; i < gamePositionsSize; i++) {
            for (int j = 0; j < gamePositionsSize; j++) {
                positions[i][j] = defaultPlayerSymbol;
            }
        }

        return new GamePositions(positions);
    }

    public Optional<GameMove> makeMove(final GameMove move, final PlayerSymbol playerSymbol) {
        final Optional<GameMove> gameMove = validateMove(move);
        if (gameMove.isPresent()) {
            positions[move.row()][move.column()] = playerSymbol;
        }
        return gameMove;
    }

    private boolean isCellAvailable(final GameMove move) {
        final PlayerSymbol gameCell = positions[move.row()][move.column()];
        return gameCell.symbol() == BLANK_PLAYER_SYMBOL_CHAR;
    }

    public Optional<GameMove> validateMove(final GameMove move) {
        if (!validateMoveBounds(move.row()) || !validateMoveBounds(move.column())) {
            return Optional.empty();
        }
        if (!isCellAvailable(move)) {
            return Optional.empty();
        }

        return Optional.of(move);
    }

    private boolean validateMoveBounds(final int value) {
        return value >= 0 && value < positions.length;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GamePositions that = (GamePositions) o;
        return Arrays.deepEquals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(positions);
    }

    public PlayerSymbol[][] positions() {
        return positions;
    }

    @Override
    public String toString() {
        return "GamePositions[" +
                "positions=" + Arrays.deepToString(positions) + ']';
    }

}
