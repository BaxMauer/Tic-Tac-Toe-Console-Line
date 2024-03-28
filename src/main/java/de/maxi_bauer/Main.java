package de.maxi_bauer;

import de.maxi_bauer.data.GameMove;
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
        List<Player> players = List.of(
                new CommandLinePlayer(new PlayerSymbol('X')),
                new CommandLinePlayer(new PlayerSymbol('Y'))
        );


        Gameboard gameboard = new Gameboard(renderer);

        int playerIndex = 0;
        Player currentPlayer = players.getFirst();

        while (gameboard.getGameState() == PLAYING) {
            gameboard.drawBoard();


            gameboard.makeMove(getMove(currentPlayer, gameboard), currentPlayer);
            gameboard.drawBoard();


            playerIndex = (playerIndex + 1) % 2;
            currentPlayer = players.get(playerIndex);
        }
    }

    public static GameMove getMove(final Player player, final Gameboard gameboard) {
        try {
            return gameboard.validateMove(player.getMove());
        } catch (RuntimeException ex) {
            System.out.println("The inserted field is not valid. Try again.");
            return getMove(player, gameboard);
        }
    }
}