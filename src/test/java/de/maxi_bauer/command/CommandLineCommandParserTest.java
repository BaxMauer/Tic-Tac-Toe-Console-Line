package de.maxi_bauer.command;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.rendering.Renderer;
import de.maxi_bauer.statistics.StatisticsHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineCommandParserTest {

    @Test
    public void testGetMoveValidCommand() {
        final String input = "1:1";
        provideInput(input);
        final Renderer renderer = Mockito.mock(Renderer.class);
        final StatisticsHandler statisticsHandler = Mockito.mock(StatisticsHandler.class);
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(statisticsHandler, renderer);


        final Optional<GameMove> actualMove = commandParser.getMove();
        assertEquals(Optional.of(new GameMove(0, 0)), actualMove);
    }

    @Test
    public void testGetMoveInvalidCommand() {
        final String input = "invalid";
        provideInput(input);
        final Renderer renderer = Mockito.mock(Renderer.class);
        final StatisticsHandler statisticsHandler = Mockito.mock(StatisticsHandler.class);
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(statisticsHandler, renderer);

        final Optional<GameMove> actualMove = commandParser.getMove();
        assertTrue(actualMove.isEmpty());
    }

    @Test
    public void testGetGameRestart() {
        final String input = "restart";
        provideInput(input);
        final Renderer renderer = Mockito.mock(Renderer.class);
        final StatisticsHandler statisticsHandler = Mockito.mock(StatisticsHandler.class);
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(statisticsHandler, renderer);


        final GameCommand actualCommand = commandParser.getGameRestart();
        assertEquals(GameCommand.INVALID, actualCommand);
    }

    @Test
    public void testGetCommandValidMove() {
        final String commandString = "1:1";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertEquals(GameCommand.MOVE, commandParser.getCommand(commandString));
    }

    @Test
    public void testGetCommandValidEnd() {
        final String commandString = "e";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertEquals(GameCommand.END, commandParser.getCommand(commandString));
    }

    @Test
    public void testGetCommandValidStatistics() {
        final String commandString = "p";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertEquals(GameCommand.STATISTICS, commandParser.getCommand(commandString));
    }

    @Test
    public void testGetCommandValidGameRestart() {
        final String commandString = "";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertEquals(GameCommand.NEXT_GAME, commandParser.getCommand(commandString));
    }

    @Test
    public void testGetCommandInvalid() {
        final String commandString = "invalid";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertEquals(GameCommand.INVALID, commandParser.getCommand(commandString));
    }

    @Test
    public void testExtractGameMoveValid() {
        final String gameMoveString = "1:1";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        final Optional<GameMove> actualMove = commandParser.extractGameMove(gameMoveString);
        assertEquals(Optional.of(new GameMove(0, 0)), actualMove);
    }

    @Test
    public void testExtractGameMoveInvalid() {
        final String gameMoveString = "invalid";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        final Optional<GameMove> actualMove = commandParser.extractGameMove(gameMoveString);
        assertTrue(actualMove.isEmpty());
    }

    @Test
    public void testIsGameMoveValid() {
        final String commandString = "1:1";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertTrue(commandParser.isGameMove(commandString));
    }

    @Test
    public void testIsGameMoveInvalid() {
        final String commandString = "invalid";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertFalse(commandParser.isGameMove(commandString));
    }

    @Test
    public void testIsGameRestartValid() {
        final String commandString = "";
        final CommandLineCommandParser commandParser = new CommandLineCommandParser(Mockito.mock(StatisticsHandler.class), Mockito.mock(Renderer.class));
        assertTrue(commandParser.isGameRestart(commandString));
    }

    private void provideInput(final String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}