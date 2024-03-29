package de.maxi_bauer;

import de.maxi_bauer.board.BoringGameboardWinChecker;
import de.maxi_bauer.board.Gameboard;
import de.maxi_bauer.board.GameboardWinChecker;
import de.maxi_bauer.player.CommandLinePlayer;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.player.PlayerSymbol;
import de.maxi_bauer.rendering.BoardRenderer;
import de.maxi_bauer.rendering.CommandLineBoardRenderer;

import java.util.List;

import static de.maxi_bauer.data.GameState.PLAYING;

public class Main {
    public static void main(String[] args) {

        BoardRenderer renderer = new CommandLineBoardRenderer();
        GameboardWinChecker winChecker = new BoringGameboardWinChecker();
        List<Player> players = List.of(
                new CommandLinePlayer(new PlayerSymbol('X')),
                new CommandLinePlayer(new PlayerSymbol('Y'))
        );


        Gameboard gameboard = new Gameboard(renderer, winChecker);

        int playerIndex = 0;
        Player currentPlayer = players.getFirst();

        while (gameboard.getGameState() == PLAYING) {
            gameboard.drawBoard();


            makeMove(currentPlayer, gameboard);
            gameboard.drawBoard();


            playerIndex = (playerIndex + 1) % 2;
            currentPlayer = players.get(playerIndex);
        }
    }

    public static void makeMove(final Player player, final Gameboard gameboard) {
        try {
            gameboard.makeMove(player.getMove(), player);
        } catch (RuntimeException ex) {
            System.out.println("The inserted field is not valid. Try again.");
            makeMove(player, gameboard);
        }
    }
}