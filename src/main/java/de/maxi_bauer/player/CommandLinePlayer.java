package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.menu.CommandParser;

import java.util.Scanner;

public class CommandLinePlayer implements Player {
    private final CommandParser commandLineParser;
    PlayerSymbol playerSymbol;
    Scanner scanner;

    public CommandLinePlayer(final PlayerSymbol symbol, final CommandParser commandLineParser) {
        this.playerSymbol = symbol;
        this.scanner = new Scanner(System.in);
        this.commandLineParser = commandLineParser;
    }

    @Override
    public GameMove getMove() {
        return commandLineParser.getMove();
    }

    @Override
    public PlayerSymbol getSymbol() {
        return this.playerSymbol;
    }
}
