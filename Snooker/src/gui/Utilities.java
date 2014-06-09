package gui;

import java.awt.Color;
import java.awt.Dimension;

public class Utilities {
	public static Dimension dimScreen = new Dimension(1280, 800);
	public static Dimension optionsMenuSize = new Dimension(600, 600);
	public static Color[] ballsColors = { Color.WHITE, Color.RED.darker(), Color.YELLOW.darker(),
			Color.GREEN, new Color(150, 75, 0), Color.BLUE.darker(),
			Color.PINK.darker(), Color.BLACK };
	
	public static String rulesStr = "The player that has the highest score on after all balls are potted wins.\n\nPoints:\n RED - 1\n YELLOW - 2\n GREEN - 3\n BROWN - 4\n BLUE - 5\n PINK - 6\n BLACK - 7\n\nThe balls have to be potted in a determined sequence:\n if there are red balls on the table\n  - red ball, colored ball, red ball, colored ball...\n (if the turns change, the sequence should begin again with a red ball)\n\n else\n - colored balls ordered by value (yellow, green, ... black)\n\nFaults:\n - white ball potted\n - first ball hit is non valid\n - no ball is hit\n\nIf you commit any of the faults above, you lose your turn\nand your opponent gets 4 points.";
}
