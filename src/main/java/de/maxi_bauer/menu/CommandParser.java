package de.maxi_bauer.menu;

import de.maxi_bauer.board.GameMove;

public interface CommandParser {
    GameMove getMove();

    GameCommand getGameRestart();
}
