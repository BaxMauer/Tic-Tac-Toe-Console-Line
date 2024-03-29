package de.maxi_bauer.board;

import de.maxi_bauer.data.GamePositions;
import de.maxi_bauer.player.PlayerSymbol;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;

import static de.maxi_bauer.config.GameConfig.BLANK_PLAYER_SYMBOL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BoringGameboardWinCheckerTest {

    private static final PlayerSymbol X_SYMBOL = new PlayerSymbol('X');
    public static final int MIN_POSITION_SIZE = 3;
    public static final int MAX_POSITION_SIZE = 11;

    private static BoringGameboardWinChecker winChecker;

    @BeforeAll
    static void setUp() {
        winChecker = new BoringGameboardWinChecker();
    }

    @ParameterizedTest
    @MethodSource("positionSizeParameters")
    void isGameWon_WinInRows_ReturnsTrue(int size) {
        // Arrange
        PlayerSymbol[][] positions = new PlayerSymbol[size][size];
        for (int testRow = 0; testRow < size; testRow++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    positions[i][j] = i == testRow ? X_SYMBOL : BLANK_PLAYER_SYMBOL;
                }
            }
            GamePositions gamePositions = new GamePositions(positions);

            // Act
            boolean result = winChecker.isGameWon(gamePositions);

            // Assert
            String resultString = "Testing row " + testRow;
            assertTrue(result, resultString);
        }
    }

    @ParameterizedTest
    @MethodSource("positionSizeParameters")
    void isGameWon_WinInColumns_ReturnsTrue(int size) {
        // Arrange
        PlayerSymbol[][] positions = new PlayerSymbol[size][size];
        for (int testColumn = 0; testColumn < size; testColumn++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    positions[j][i] = i == testColumn ? X_SYMBOL : BLANK_PLAYER_SYMBOL;
                }
            }
            GamePositions gamePositions = new GamePositions(positions);

            // Act
            boolean result = winChecker.isGameWon(gamePositions);

            // Assert
            String resultString = "Testing column " + testColumn;
            assertTrue(result, resultString);
        }
    }

    @ParameterizedTest
    @MethodSource("positionSizeParameters")
    void isGameWon_WinInDiagonal_ReturnsTrue(int size) {
        // Arrange
        PlayerSymbol[][] positions = new PlayerSymbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions[i][j] = i == j ? X_SYMBOL : BLANK_PLAYER_SYMBOL;
            }
        }
        GamePositions gamePositions = new GamePositions(positions);

        // Act
        boolean result = winChecker.isGameWon(gamePositions);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("positionSizeParameters")
    void isGameWon_WinInAntiDiagonal_ReturnsTrue(int size) {
        // Arrange
        PlayerSymbol[][] positions = new PlayerSymbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                positions[i][j] = i == (size - 1) - j ? X_SYMBOL : BLANK_PLAYER_SYMBOL;
            }
        }
        GamePositions gamePositions = new GamePositions(positions);

        // Act
        boolean result = winChecker.isGameWon(gamePositions);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("positionSizeParameters")
    void isGameWon_NoWin_ReturnsFalse(int size) {
        // Arrange
        PlayerSymbol[][] positions = new PlayerSymbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions[i][j] = (i == size - 1 && j == size - 1) ? X_SYMBOL : BLANK_PLAYER_SYMBOL;
            }
        }
        GamePositions gamePositions = new GamePositions(positions);

        // Act
        boolean result = winChecker.isGameWon(gamePositions);

        // Assert
        assertFalse(result);
    }


    /***
     * Generates the parameters for testing with different field sizes
     * @return the list with the different sizes
     */
    static private List<Integer> positionSizeParameters() {
        return IntStream.range(MIN_POSITION_SIZE, MAX_POSITION_SIZE).boxed().toList();
    }
}
