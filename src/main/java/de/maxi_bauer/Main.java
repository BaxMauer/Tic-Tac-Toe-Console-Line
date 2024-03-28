package de.maxi_bauer;

import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.rendering.BoardRenderer;
import de.maxi_bauer.rendering.CommandLineBoardRenderer;

public class Main {
    public static void main(String[] args) {

        BoardRenderer cmdLineRenderer = new CommandLineBoardRenderer();

        Gameboard gameboard = new Gameboard(cmdLineRenderer);

        gameboard.makeMove(new GameMove(0,0));
        gameboard.drawBoard();
    }
}