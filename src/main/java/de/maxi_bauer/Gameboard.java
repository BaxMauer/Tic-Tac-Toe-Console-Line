package de.maxi_bauer;

import de.maxi_bauer.config.GameConfig;
import de.maxi_bauer.data.GameMove;
import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.player.Player;
import de.maxi_bauer.rendering.BoardRenderer;

public class Gameboard {
    private final GamePositions positions = GamePositions.newPositions(GameConfig.GAME_POSITIONS_SIZE);
    private final BoardRenderer boardRenderer;

    public Gameboard(BoardRenderer boardRenderer) {
        this.boardRenderer = boardRenderer;
    }

    public void drawBoard(){
        boardRenderer.renderBoard(this.positions);
    }

    public boolean makeMove(GameMove move, Player player) {
        positions.positions()[move.row() - 1][move.column() - 1] = player.getSymbol();
        return true;
    }
}
