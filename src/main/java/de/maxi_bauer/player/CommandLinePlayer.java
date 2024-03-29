package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;

import java.util.Scanner;

public class CommandLinePlayer implements Player {
    PlayerSymbol playerSymbol;
    Scanner scanner;

    public CommandLinePlayer(final PlayerSymbol symbol) {
        this.playerSymbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public GameMove getMove() {
        System.out.printf("%c: Please enter the position of your mark (Row:Column):", playerSymbol.symbol());
        try {
            return readGameMoveFromCommandLine();
        } catch (RuntimeException ex) {
            System.out.println("The inserted field is not valid. Try again.");
            return getMove();
        }
    }

    @Override
    public PlayerSymbol getSymbol() {
        return this.playerSymbol;
    }

    private GameMove readGameMoveFromCommandLine() {
        String GameMoveString = scanner.nextLine();
        return extractGameMove(GameMoveString);
    }

    private GameMove extractGameMove(final String gameMoveString) {
        String[] result = gameMoveString.split(":");
        if (result.length != 2) {
            throw new IllegalArgumentException();
        }
        int row = Integer.parseInt(result[0]) - 1;
        int col = Integer.parseInt(result[1]) - 1;


        return new GameMove(row, col);
    }
}
