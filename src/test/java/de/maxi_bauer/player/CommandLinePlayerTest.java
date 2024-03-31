package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.command.CommandParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CommandLinePlayerTest {

    @Test
    public void testGetMove() {
        // Mock CommandParser
        final CommandParser commandParser = Mockito.mock(CommandParser.class);

        // Create a CommandLinePlayer with a mocked CommandParser and PlayerSymbol 'X'
        final PlayerSymbol playerSymbol = new PlayerSymbol('X');
        final CommandLinePlayer player = new CommandLinePlayer(playerSymbol, commandParser);

        // Mock the behavior of CommandParser to return a specific GameMove
        final GameMove expectedMove = new GameMove(1, 2); // Example move
        when(commandParser.getMove()).thenReturn(Optional.of(expectedMove));

        // Call the getMove method and assert the returned GameMove
        final Optional<GameMove> actualMove = player.getMove();
        assertEquals(Optional.of(expectedMove), actualMove);
    }

    @Test
    public void testGetSymbol() {
        // Create a CommandLinePlayer with a PlayerSymbol 'O' and a mocked CommandParser
        final PlayerSymbol playerSymbol = new PlayerSymbol('O');
        final CommandParser commandParser = Mockito.mock(CommandParser.class);
        final CommandLinePlayer player = new CommandLinePlayer(playerSymbol, commandParser);

        // Call the getSymbol method and assert the returned PlayerSymbol
        final PlayerSymbol actualSymbol = player.getSymbol();
        assertEquals(playerSymbol, actualSymbol);
    }

    @Test
    public void testGetMoveReturnsEmptyOptional() {
        // Mock CommandParser to return an empty Optional
        final CommandParser commandParser = Mockito.mock(CommandParser.class);
        when(commandParser.getMove()).thenReturn(Optional.empty());

        // Create a CommandLinePlayer with the mocked CommandParser and PlayerSymbol 'X'
        final PlayerSymbol playerSymbol = new PlayerSymbol('X');
        final CommandLinePlayer player = new CommandLinePlayer(playerSymbol, commandParser);

        // Call the getMove method and assert that it returns an empty Optional
        final Optional<GameMove> actualMove = player.getMove();
        assertTrue(actualMove.isEmpty());
    }

    @Test
    public void testGetMoveThrowsException() {
        // Mock CommandParser to throw an exception
        final CommandParser commandParser = Mockito.mock(CommandParser.class);
        when(commandParser.getMove()).thenThrow(new RuntimeException("Error parsing move"));

        // Create a CommandLinePlayer with the mocked CommandParser and PlayerSymbol 'X'
        final PlayerSymbol playerSymbol = new PlayerSymbol('X');
        final CommandLinePlayer player = new CommandLinePlayer(playerSymbol, commandParser);

        // Call the getMove method and assert that it throws an exception
        final Exception exception = assertThrows(RuntimeException.class, player::getMove);
        assertEquals("Error parsing move", exception.getMessage());
    }
}
