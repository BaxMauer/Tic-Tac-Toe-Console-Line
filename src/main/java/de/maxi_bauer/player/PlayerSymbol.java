package de.maxi_bauer.player;

import java.util.Objects;

public record PlayerSymbol(char symbol) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSymbol that = (PlayerSymbol) o;
        return symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
