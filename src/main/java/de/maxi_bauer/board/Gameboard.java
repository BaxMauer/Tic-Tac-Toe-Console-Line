package de.maxi_bauer.board;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.Renderer;

import java.util.Arrays;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL;

public class Gameboard {
    private GamePositions positions = GamePositions.newPositions(GameConfig.GAME_POSITIONS_SIZE);
    private final Renderer renderer;
    private final GameboardWinChecker winChecker;
    private GameState gameState = GameState.PLAYING;

    public Gameboard(final Renderer renderer, final GameboardWinChecker winChecker) {
        this.renderer = renderer;
        this.winChecker = winChecker;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void drawBoard(){
        renderer.renderBoard(this.positions);
    }

    public void makeMove(final Player player) {
        validateAndMakeMove(player);

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

    public void resetGame() {
        this.positions = resetGame(this.positions);
    }

    private GamePositions resetGame(final GamePositions positions) {
        gameState = GameState.PLAYING;
        return GamePositions.newPositions(positions.positions().length);
    }

    private void validateAndMakeMove(final Player player) {
        renderer.message(String.format("%c: Please enter the position of your mark (Row:Column):", player.getSymbol().symbol()));
        try {
            positions.makeMove(player.getMove(), player.getSymbol());
        } catch (final Exception ex) {
            renderer.message("The inserted field is not valid. Try again.");
            validateAndMakeMove(player);
        }
    }
}
