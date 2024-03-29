package de.maxi_bauer.player;

import de.maxi_bauer.data.GameMove;

public interface Player {
    GameMove getMove();

    PlayerSymbol getSymbol();
}
