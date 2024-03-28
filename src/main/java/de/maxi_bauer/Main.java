package de.maxi_bauer;

import de.maxi_bauer.player.CommandLinePlayer;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.BoardRenderer;
import de.maxi_bauer.rendering.CommandLineBoardRenderer;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BoardRenderer cmdLineRenderer = new CommandLineBoardRenderer();
        List<Player> players = List.of(
                new CommandLinePlayer('X'),
                new CommandLinePlayer('Y')
        );


        Gameboard gameboard = new Gameboard(cmdLineRenderer);
        gameboard.makeMove(players.getFirst().getMove(), players.getFirst());
        gameboard.drawBoard();
    }
}