package de.maxi_bauer;

import de.maxi_bauer.board.BoringGameboardWinChecker;
import de.maxi_bauer.board.Gameboard;
import de.maxi_bauer.board.GameboardWinChecker;
import de.maxi_bauer.menu.CommandLineCommandParser;
import de.maxi_bauer.menu.CommandParser;
import de.maxi_bauer.player.CommandLinePlayer;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.rendering.CommandLineRenderer;
import de.maxi_bauer.rendering.Renderer;

import java.util.List;

import static de.maxi_bauer.board.GameState.PLAYING;

public class Main {
    public static void main(String[] args) {

        Renderer renderer = new CommandLineRenderer();
        GameboardWinChecker winChecker = new BoringGameboardWinChecker();
        CommandParser commandParser = new CommandLineCommandParser();

        List<Player> players = List.of(
                new CommandLinePlayer(new PlayerSymbol('X'), commandParser),
                new CommandLinePlayer(new PlayerSymbol('Y'), commandParser)
        );


        Gameboard gameboard = new Gameboard(renderer, winChecker);

        do {
            int playerIndex = -1;
            Player currentPlayer = players.getFirst();

            while (gameboard.getGameState() == PLAYING) {
                playerIndex = (playerIndex + 1) % 2;
                currentPlayer = players.get(playerIndex);

                gameboard.drawBoard();
                gameboard.makeMove(currentPlayer);
                gameboard.drawBoard();
            }

            switch (gameboard.getGameState()) {
                case PLAYING -> throw new IllegalStateException("We should not be here");
                case DRAW -> renderer.message("It's a draw! Press enter to start a new round");
                case WON -> {
                    renderer.message(String.format("\n %c won. Press enter to start a new round", currentPlayer.getSymbol().symbol()));
                }
            }
            commandParser.getGameRestart();
            gameboard.resetGame();
        } while (true);
    }
}