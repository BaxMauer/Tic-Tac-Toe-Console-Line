package de.maxi_bauer.board;

import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL;

public class BoringGameboardWinChecker implements GameboardWinChecker {

    @Override
    public boolean isGameWon(GamePositions gamePositions) {
        int gameSize = gamePositions.positions().length;
        PlayerSymbol[][] positions = gamePositions.positions();

        // check rows
        for (int i = 0; i < gameSize; i++) {
            PlayerSymbol checkPlayer = positions[i][0];
            for (int j = 1; j < gameSize; j++) {
                if (positions[i][j] != checkPlayer || checkPlayer.equals(BLANK_PLAYER_SYMBOL)) {
                    break;
                }
                if (j == gameSize - 1) {
                    return true;
                }
            }
        }

        // check columns
        for (int i = 0; i < gameSize; i++) {
            PlayerSymbol checkPlayer = positions[0][i];
            for (int j = 1; j < gameSize; j++) {
                if (positions[j][i] != checkPlayer || checkPlayer.equals(BLANK_PLAYER_SYMBOL)) {
                    break;
                }
                if (j == gameSize - 1) {
                    return true;
                }
            }
        }

        // check diagonal
        PlayerSymbol checkPlayerDia = positions[0][0];
        for (int i = 1; i < gameSize; i++) {
            if (positions[i][i] != checkPlayerDia || checkPlayerDia.equals(BLANK_PLAYER_SYMBOL)) {
                break;
            }
            if (i == gameSize - 1) {
                return true;
            }
        }

        // check anti diagonal
        PlayerSymbol checkPlayerAntiDia = positions[0][gameSize - 1];
        for (int i = gameSize - 2, j = 1; i >= 0; i--, j++) {
            if (positions[j][i] != checkPlayerAntiDia || checkPlayerAntiDia.equals(BLANK_PLAYER_SYMBOL)) {
                break;
            }
            if (j == gameSize - 1) {
                return true;
            }
        }
        return false;
    }
}
