package de.maxi_bauer.board;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.Renderer;
import de.maxi_bauer.statistics.StatisticsHandler;

import java.util.Arrays;
import java.util.Optional;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL;

public class Gameboard {
    private GamePositions positions = GamePositions.newPositions(GameConfig.GAME_POSITIONS_SIZE);
    private final Renderer renderer;
    private final GameboardWinChecker winChecker;
    private final StatisticsHandler statisticsHandler;
    private GameState gameState = GameState.PLAYING;

    public Gameboard(final Renderer renderer, final GameboardWinChecker winChecker, final StatisticsHandler statisticsHandler) {
        this.renderer = renderer;
        this.winChecker = winChecker;
        this.statisticsHandler = statisticsHandler;
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
            statisticsHandler.registerWin(player.getSymbol());
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
            final Optional<GameMove> move = player.getMove();
            if (move.isEmpty() || positions.makeMove(move.get(), player.getSymbol()).isEmpty()) {
                renderer.message("The inserted field is not valid. Try again.");
                validateAndMakeMove(player);
            }
        } catch (final IllegalArgumentException | IllegalStateException ex) {
            renderer.message("The inserted field is not valid. Try again.");
            validateAndMakeMove(player);
        }
    }
}
