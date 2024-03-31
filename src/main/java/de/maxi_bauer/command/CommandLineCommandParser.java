package de.maxi_bauer.command;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.rendering.Renderer;
import de.maxi_bauer.statistics.StatisticsHandler;

import java.util.Optional;
import java.util.Scanner;

public class CommandLineCommandParser implements CommandParser {

    private final StatisticsHandler statisticsHandler;
    private final Renderer renderer;
    Scanner scanner;

    public CommandLineCommandParser(final StatisticsHandler statisticsHandler, final Renderer renderer) {
        this.statisticsHandler = statisticsHandler;
        this.renderer = renderer;
        this.scanner = new Scanner(System.in);
    }

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

    @Override
    public GameCommand getGameRestart() {
        return getCommand(readStringFromCommandLine());
    }

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

    private void endGame() {
        System.exit(0);
    }

    private String readStringFromCommandLine() {
        return scanner.nextLine();
    }

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

    private boolean isGameMove(final String commandString) {
        return commandString.matches("[1-9]+:[1-9]+");
    }


    private boolean isGameRestart(final String commandString) {
        return commandString.equalsIgnoreCase("");
    }

    private boolean isGetStatistics(final String commandString) {
        return commandString.equalsIgnoreCase("p");
    }

    private boolean isGameEnd(final String commandString) {
        return commandString.equalsIgnoreCase("e");
    }
}
