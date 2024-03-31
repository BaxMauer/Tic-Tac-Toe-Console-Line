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
import de.maxi_bauer.statistics.InMemoryStatistics;
import de.maxi_bauer.statistics.StatisticsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.maxi_bauer.board.GameState.PLAYING;

public class Main {
    public static void main(final String[] args) {

        final Renderer renderer = new CommandLineRenderer();
        final GameboardWinChecker winChecker = new BoringGameboardWinChecker();
        final CommandParser commandParser = new CommandLineCommandParser();
        final StatisticsHandler statisticsHandler = new InMemoryStatistics();

        final List<Player> players = List.of(
                new CommandLinePlayer(new PlayerSymbol('X'), commandParser),
                new CommandLinePlayer(new PlayerSymbol('Y'), commandParser)
        );


        final Gameboard gameboard = new Gameboard(renderer, winChecker, statisticsHandler);

        Player currentPlayer = players.getFirst();
        int playerIndex = 0;
        do {
            while (gameboard.getGameState() == PLAYING) {
                currentPlayer = players.get(playerIndex);

                gameboard.drawBoard();
                gameboard.makeMove(currentPlayer);
                gameboard.drawBoard();
                playerIndex = (playerIndex + 1) % players.size();
            }

            switch (gameboard.getGameState()) {
                case PLAYING -> throw new IllegalStateException("We should not be here");
                case DRAW -> {
                    renderer.message("It's a draw! Press enter to start a new round");
                    playerIndex = 0;
                }
                case WON -> {
                    renderer.message(String.format("\n %c won. Press enter to start a new round", currentPlayer.getSymbol().symbol()));
                    playerIndex = players.indexOf(getRandomPlayerThatDidntWin(players, currentPlayer));
                }
            }
            commandParser.getGameRestart();
            gameboard.resetGame();
        } while (true);
    }

    private static Player getRandomPlayerThatDidntWin(final List<Player> players, final Player currentPlayer) {
        final ArrayList<Player> randomList = new ArrayList<>(players);
        randomList.remove(currentPlayer);
        Collections.shuffle(randomList);
        return randomList.getFirst();
    }
}