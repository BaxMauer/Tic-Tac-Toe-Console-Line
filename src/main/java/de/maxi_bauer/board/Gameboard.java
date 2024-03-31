package de.maxi_bauer.board;

import de.maxi_bauer.config.GameConstants;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.Renderer;
import de.maxi_bauer.statistics.StatisticsHandler;

import java.util.Arrays;
import java.util.Optional;

import static de.maxi_bauer.config.GameConstants.BLANK_PLAYER_SYMBOL;

/**
 * Represents the game board and manages game logic.
 */
public class Gameboard {
    private GamePositions positions = GamePositions.newPositions(GameConstants.GAME_POSITIONS_SIZE);
    private final Renderer renderer;
    private final GameboardWinChecker winChecker;
    private final StatisticsHandler statisticsHandler;
    private GameState gameState = GameState.PLAYING;

    /**
     * Constructs a Gameboard with the specified renderer, win checker, and statistics handler.
     *
     * @param renderer          The renderer for displaying the game board.
     * @param winChecker        The win checker for determining game win conditions.
     * @param statisticsHandler The statistics handler for tracking game statistics.
     */
    public Gameboard(final Renderer renderer, final GameboardWinChecker winChecker, final StatisticsHandler statisticsHandler) {
        this.renderer = renderer;
        this.winChecker = winChecker;
        this.statisticsHandler = statisticsHandler;
    }

    /**
     * Retrieves the current state of the game.
     *
     * @return The current {@link GameState} of the game.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Draws the current state of the game board.
     */
    public void drawBoard(){
        renderer.renderBoard(this.positions);
    }

    /**
     * Makes a move on the game board for the specified player.
     * Validates the move, updates the board, and checks for win or draw conditions.
     *
     * @param player The player making the move.
     */
    public void makeMove(final Player player) {
        validateAndMakeMove(player);

        if (winChecker.isGameWon(positions)) {
            gameState = GameState.WON;
            statisticsHandler.registerWin(player.getSymbol());
        } else if (isDraw(positions)) {
            gameState = GameState.DRAW;
        }
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        this.positions = resetGame(this.positions);
    }

    /***
     * Reserts the {@link GameState} of the board and the positions.
     * @param positions {@link GamePositions} to be reset.
     * @return reset {@link GamePositions}.
     */
    private GamePositions resetGame(final GamePositions positions) {
        gameState = GameState.PLAYING;
        return GamePositions.newPositions(positions.positions().length);
    }

    /***
     * Checks if the game is a draw.
     * @param positions {@link GamePositions} to be checked.
     * @return True if the Game is a draw, false otherwise.
     */
    private boolean isDraw(final GamePositions positions) {
        return Arrays.stream(positions.positions())
                .flatMap(Arrays::stream)
                .noneMatch(ps -> ps.equals(BLANK_PLAYER_SYMBOL));
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
