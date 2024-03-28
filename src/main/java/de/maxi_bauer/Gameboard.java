package de.maxi_bauer;

import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.rendering.BoardRenderer;

public class Gameboard {
    public static final int GAME_POSITIONS_SIZE = 3;
    private final GamePositions positions = GamePositions.newPositions(GAME_POSITIONS_SIZE);
    private final BoardRenderer boardRenderer;

    public Gameboard(BoardRenderer boardRenderer) {
        this.boardRenderer = boardRenderer;
    }

    public void drawBoard(){
        boardRenderer.renderBoard(this.positions);
    }

    public boolean makeMove(GameMove move){
        positions.positions()[move.row()][move.column()] = 'X';
        return true;
    }
}
