package de.maxi_bauer;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.data.GameState;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.rendering.BoardRenderer;

import static de.maxi_bauer.config.GameConfig.BlankPlayerSymbol;

public class Gameboard {
    private final GamePositions positions = GamePositions.newPositions(GameConfig.GAME_POSITIONS_SIZE);
    private final BoardRenderer boardRenderer;
    private final GameState gameState = GameState.PLAYING;
    public Gameboard(BoardRenderer boardRenderer) {
        this.boardRenderer = boardRenderer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void drawBoard(){
        boardRenderer.renderBoard(this.positions);
    }

    public boolean makeMove(GameMove move, Player player) {
        positions.positions()[move.row()][move.column()] = player.getSymbol();
        return true;
    }

    public GameMove validateMove(GameMove move) {
        if (!validateMoveBounds(move.row()) || !validateMoveBounds(move.column())) {
            throw new IllegalArgumentException();
        }
        if (!isCellAvailable(move)) {
            throw new IllegalArgumentException();
        }

        return move;
    }

    private boolean validateMoveBounds(final int value) {
        return value >= 0 && value < GameConfig.GAME_POSITIONS_SIZE;
    }

    private boolean isCellAvailable(GameMove move) {
        PlayerSymbol gameCell = positions.positions()[move.row()][move.column()];
        return gameCell.symbol() == BlankPlayerSymbol;
    }
}
