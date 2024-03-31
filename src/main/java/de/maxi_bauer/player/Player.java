package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;

public interface Player {
    GameMove getMove();

    PlayerSymbol getSymbol();
}
