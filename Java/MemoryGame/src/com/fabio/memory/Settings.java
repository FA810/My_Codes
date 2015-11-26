package com.fabio.memory;

public class Settings {

	// console game settings
	public static String buttonForQuit = "ENTER";
	public static String buttonForBoard = "b";
	public static String buttonForMoveList = "m";
	public static String buttonForOptions = "o";
	public static String buttonForSolution = "r";
	public static String buttonForRestart = "s";
	public static String buttonForExit = "e";
	public static boolean showCompleteBoardOnStartup = true;

	// GUI game settings
	public static Integer preferredButtonSizeInGUI = 60;
	public static Integer preferredProgressBarSizeInGUI = 20;
	public static Integer preferredTextAreaSizeInGUI = 250;
	public static Integer preferredWindowSizeInGUI = preferredButtonSizeInGUI
			* 4 + preferredProgressBarSizeInGUI + preferredTextAreaSizeInGUI
			+ 26;
	public static boolean useNimbusLookAndFeel = false;
	public static boolean shuffleDispositionForTheGame = false;
}
