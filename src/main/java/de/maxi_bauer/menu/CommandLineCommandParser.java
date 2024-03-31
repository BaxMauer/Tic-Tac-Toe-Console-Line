package de.maxi_bauer.menu;

import de.maxi_bauer.board.GameMove;

import java.util.Scanner;

public class CommandLineCommandParser implements CommandParser {

    Scanner scanner;

    public CommandLineCommandParser() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public GameMove getMove() {
        final String commandString = readStringFromCommandLine();

        switch (getCommand(commandString)) {
            case MOVE -> {
                return extractGameMove(commandString);
            }
            case END -> endGame();

            case STATISTICS -> {
                return new GameMove(1, 1);
            }
            default -> throw new IllegalArgumentException("Invalid command!");
        }

        return null;
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

    private GameMove extractGameMove(final String gameMoveString) {
        final String[] result = gameMoveString.split(":");
        if (result.length != 2) {
            throw new IllegalArgumentException();
        }
        final int row = Integer.parseInt(result[0]) - 1;
        final int col = Integer.parseInt(result[1]) - 1;


        return new GameMove(row, col);
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
