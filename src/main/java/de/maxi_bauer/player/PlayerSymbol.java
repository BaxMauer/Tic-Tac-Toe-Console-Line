package de.maxi_bauer.player;

import java.util.Objects;

/**
 * Represents a symbol associated with a player.
 */
public record PlayerSymbol(char symbol) {
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerSymbol that = (PlayerSymbol) o;
        return symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
