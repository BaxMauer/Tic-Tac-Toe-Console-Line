package de.maxi_bauer.board;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.data.GameState;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.rendering.BoardRenderer;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL_CHAR;

public class Gameboard {
    private final GamePositions positions = GamePositions.newPositions(GameConfig.GAME_POSITIONS_SIZE);
    private final BoardRenderer boardRenderer;
    private final GameboardWinChecker winChecker;
    private GameState gameState = GameState.PLAYING;

    public Gameboard(BoardRenderer boardRenderer, GameboardWinChecker winChecker) {
        this.boardRenderer = boardRenderer;
        this.winChecker = winChecker;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void drawBoard(){
        boardRenderer.renderBoard(this.positions);
    }

    public boolean makeMove(GameMove move, Player player) {
        positions.positions()[move.row()][move.column()] = player.getSymbol();
        checkWinState();
        return true;
    }

    public void checkWinState() {
        if (winChecker.isGameWon(positions)) {
            this.gameState = GameState.ENDED;
        }
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
        return gameCell.symbol() == BLANK_PLAYER_SYMBOL_CHAR;
    }
}
