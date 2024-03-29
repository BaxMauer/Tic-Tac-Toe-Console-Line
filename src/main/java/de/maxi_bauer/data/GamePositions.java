package de.maxi_bauer.data;

import de.maxi_bauer.player.PlayerSymbol;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL_CHAR;

public record GamePositions(PlayerSymbol[][] positions) {

    public static GamePositions newPositions(final int gamePositionsSize){
        PlayerSymbol defaultPlayerSymbol = new PlayerSymbol(BLANK_PLAYER_SYMBOL_CHAR);
        PlayerSymbol[][] positions = new PlayerSymbol[gamePositionsSize][gamePositionsSize];

        for (int i = 0; i < gamePositionsSize; i++) {
            for (int j = 0; j < gamePositionsSize; j++) {
                positions[i][j] = defaultPlayerSymbol;
            }
        }

        return new GamePositions(positions);
    }
}
