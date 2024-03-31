package de.maxi_bauer.player;

import de.maxi_bauer.board.GameMove;
import de.maxi_bauer.command.CommandParser;

import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a player who interacts with the game via the command line.
 */
public class CommandLinePlayer implements Player {
    private final CommandParser commandLineParser;
    final PlayerSymbol playerSymbol;
    final Scanner scanner;

    /**
     * Constructs a CommandLinePlayer with the specified symbol and command parser.
     *
     * @param symbol            The {@link PlayerSymbol} associated with the player.
     * @param commandLineParser The {@link CommandParser} to use for parsing player input.
     */
    public CommandLinePlayer(final PlayerSymbol symbol, final CommandParser commandLineParser) {
        this.playerSymbol = symbol;
        this.scanner = new Scanner(System.in);
        this.commandLineParser = commandLineParser;
    }

    /**
     * Retrieves a {@link GameMove} made by the player.
     *
     * @return An optional {@link GameMove} made by the player.
     */
    @Override
    public Optional<GameMove> getMove() {
        return commandLineParser.getMove();
    }

    /**
     * Retrieves the {@link PlayerSymbol} associated with the player.
     *
     * @return The {@link PlayerSymbol} associated with the player.
     */
    @Override
    public PlayerSymbol getSymbol() {
        return this.playerSymbol;
    }
}
