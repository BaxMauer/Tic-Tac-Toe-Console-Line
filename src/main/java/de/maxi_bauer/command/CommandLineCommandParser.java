package de.maxi_bauer.command;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.rendering.Renderer;
import de.maxi_bauer.statistics.StatisticsHandler;

import java.util.Optional;
import java.util.Scanner;

/**
 * Parses commands from the command line interface for the game.
 */
public class CommandLineCommandParser implements CommandParser {

    private final StatisticsHandler statisticsHandler;
    private final Renderer renderer;
    final Scanner scanner;

    /**
     * Constructs a CommandLineCommandParser with the specified statistics handler and renderer.
     *
     * @param statisticsHandler The statistics handler to use.
     * @param renderer          The renderer to use.
     */
    public CommandLineCommandParser(final StatisticsHandler statisticsHandler, final Renderer renderer) {
        this.statisticsHandler = statisticsHandler;
        this.renderer = renderer;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves a move entered by the user from the command line.
     *
     * @return The optional {@link GameMove} entered by the user.
     */
    @Override
    public Optional<GameMove> getMove() {
        final String commandString = readStringFromCommandLine();

        switch (getCommand(commandString)) {
            case MOVE -> {
                return extractGameMove(commandString);
            }
            case END -> endGame();

            case STATISTICS -> {
                renderer.renderStatistics(statisticsHandler.getStatistics());
                readStringFromCommandLine();
            }
            default -> throw new IllegalArgumentException("Invalid command!");
        }

        return Optional.empty();
    }

    /**
     * Retrieves the command for restarting the game.
     *
     * @return The {@link GameCommand} for restarting the game.
     */
    @Override
    public GameCommand getGameRestart() {
        return getCommand(readStringFromCommandLine());
    }

    /**
     * Retrieves a {@link GameCommand} entered by the user from the command line.
     *
     * @param commandString The command entered by the user.
     * @return The {@link GameCommand} corresponding to the entered command.
     */
    private GameCommand getCommand(final String commandString) {
        if (isGameMove(commandString)) {
            return GameCommand.MOVE;
        }
        if (isGetStatistics(commandString)) {
            return GameCommand.STATISTICS;
        }
        if (isGameEnd(commandString)) {
            return GameCommand.END;
        }
        if (isGameRestart(commandString)) {
            return GameCommand.NEXT_GAME;
        }

        return GameCommand.INVALID;
    }

    /**
     * Exits the game.
     */
    private void endGame() {
        System.exit(0);
    }

    /**
     * Reads a string entered by the user from the command line.
     *
     * @return The string entered by the user.
     */
    private String readStringFromCommandLine() {
        return scanner.nextLine();
    }

    /**
     * Extracts a game move from the entered command string.
     *
     * @param gameMoveString The entered command string.
     * @return An optional {@link GameMove} extracted from the command string.
     */
    private Optional<GameMove> extractGameMove(final String gameMoveString) {
        final String[] result = gameMoveString.split(":");
        if (result.length != 2) {
            return Optional.empty();
        }

        try {
            final int row = Integer.parseInt(result[0]) - 1;
            final int col = Integer.parseInt(result[1]) - 1;
            return Optional.of(new GameMove(row, col));
        } catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks if the entered command string corresponds to a game move.
     *
     * @param commandString The entered command string.
     * @return True if the command string corresponds to a game move, otherwise false.
     */
    private boolean isGameMove(final String commandString) {
        return commandString.matches("[1-9]+:[1-9]+");
    }

    /**
     * Checks if the entered command string corresponds to restarting the game.
     *
     * @param commandString The entered command string.
     * @return True if the command string corresponds to restarting the game, otherwise false.
     */
    private boolean isGameRestart(final String commandString) {
        return commandString.equalsIgnoreCase("");
    }

    /**
     * Checks if the entered command string corresponds to getting game statistics.
     *
     * @param commandString The entered command string.
     * @return True if the command string corresponds to getting game statistics, otherwise false.
     */
    private boolean isGetStatistics(final String commandString) {
        return commandString.equalsIgnoreCase("p");
    }

    /**
     * Checks if the entered command string corresponds to ending the game.
     *
     * @param commandString The entered command string.
     * @return True if the command string corresponds to ending the game, otherwise false.
     */
    private boolean isGameEnd(final String commandString) {
        return commandString.equalsIgnoreCase("e");
    }
}
