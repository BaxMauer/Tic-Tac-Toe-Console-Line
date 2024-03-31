package de.maxi_bauer.board;

import de.maxi_bauer.player.PlayerSymbol;

import java.util.Arrays;
import java.util.Optional;

import static de.maxi_bauer.config.GameConstants.BLANK_PLAYER_SYMBOL_CHAR;

/**
 * Represents the positions on the game board.
 */
public record GamePositions(PlayerSymbol[][] positions) {

    /**
     * Creates a new instance of {@link GamePositions} with the specified size.
     *
     * @param gamePositionsSize The size of the game positions.
     * @return A new instance of {@link GamePositions}.
     */
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

    /**
     * Makes a move on the game board.
     *
     * @param move         The {@link GameMove} to make.
     * @param playerSymbol The {@link PlayerSymbol} of the player making the move.
     * @return An optional {@link GameMove} representing the move made, if valid.
     */
    public Optional<GameMove> makeMove(final GameMove move, final PlayerSymbol playerSymbol) {
        final Optional<GameMove> gameMove = validateMove(move);
        if (gameMove.isPresent()) {
            positions[move.row()][move.column()] = playerSymbol;
        }
        return gameMove;
    }

    /***
     * Checks if the cell the move want to set is available or not
     * @param move The {@link GameMove} to validate.
     * @return True if the cell is available, otherwise false.
     */
    private boolean isCellAvailable(final GameMove move) {
        final PlayerSymbol gameCell = positions[move.row()][move.column()];
        return gameCell.symbol() == BLANK_PLAYER_SYMBOL_CHAR;
    }

    /**
     * Validates a {@link GameMove} on the game board.
     *
     * @param move The {@link GameMove} to validate.
     * @return An optional {@link GameMove} representing the validated move, if valid.
     */
    public Optional<GameMove> validateMove(final GameMove move) {
        if (!validateMoveBounds(move.row()) || !validateMoveBounds(move.column())) {
            return Optional.empty();
        }
        if (!isCellAvailable(move)) {
            return Optional.empty();
        }

        return Optional.of(move);
    }

    /***
     * Checks if the row or col is in the bounds of the {@link GamePositions}
     * @param value The vlue to validate.
     * @return True if the row or col is in bounds, otherwise false.
     */
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

    @Override
    public String toString() {
        return "GamePositions[" +
                "positions=" + Arrays.deepToString(positions) + ']';
    }

}
