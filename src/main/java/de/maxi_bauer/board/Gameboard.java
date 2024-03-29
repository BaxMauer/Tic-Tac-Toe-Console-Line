package de.maxi_bauer.board;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.data.GameState;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.BoardRenderer;

import java.util.Arrays;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL;

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

    public void makeMove(GameMove move, Player player) {
        positions.makeMove(move, player.getSymbol());

        if (winChecker.isGameWon(positions)) {
            gameState = GameState.WON;
        } else if (isDraw(positions)) {
            gameState = GameState.DRAW;
        }
    }

    private boolean isDraw(final GamePositions positions) {
        return Arrays.stream(positions.positions())
                .flatMap(Arrays::stream)
                .noneMatch(ps -> ps.equals(BLANK_PLAYER_SYMBOL));
    }
}
