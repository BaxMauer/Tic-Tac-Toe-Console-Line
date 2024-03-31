package de.maxi_bauer.statistics;

import java.util.List;

/**
 * A record representing game statistics, including a list of game wins.
 */
public record GameStatistics(List<GameWin> wins) {
}
