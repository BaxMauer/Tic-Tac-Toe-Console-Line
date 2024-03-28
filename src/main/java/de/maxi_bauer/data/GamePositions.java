package de.maxi_bauer.data;

public record GamePositions(char[][] positions) {

    public static GamePositions newPositions(final int gamePositionsSize){
        return new GamePositions(new char[gamePositionsSize][gamePositionsSize]);
    }
}
