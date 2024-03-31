package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;

import java.util.Optional;

public interface Player {
    Optional<GameMove> getMove();

    PlayerSymbol getSymbol();
}
