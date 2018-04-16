package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

import edu.cuny.brooklyn.project.GameSettings;

public class PuzzlerSettings {
	public final static int UNSUPPORTED_PUZZLER = -1;
	public final static int MATH_PUZZLER = 100;
	public final static int MATH_PUZZLER_PRODUCT = 101;
	public final static int MATH_PUZZLER_SQRT = 102;
	
	// ordered in difficulty
	private static int[] puzzlerTypes = {
		MATH_PUZZLER_PRODUCT,
		MATH_PUZZLER_SQRT
	};
	
	private static Random rng = new Random();
	
	public static int getRandomPuzzlerType() {
		int typeIndex = rng.nextInt(puzzlerTypes.length);
		return puzzlerTypes[typeIndex]; 
	}
	
	public static int getPuzzlerType() {
		return puzzlerTypes[GameSettings.getDifficulty() - 1];
	}
}
