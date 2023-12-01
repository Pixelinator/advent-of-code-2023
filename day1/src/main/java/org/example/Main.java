package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 1: Trebuchet?! ---
 * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
 * <p>
 * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").
 * <p>
 * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.
 * <p>
 * The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.
 * <p>
 * For example:
 * <p>
 * 1abc2
 * pqr3stu8vwx
 * a1b2c3d4e5f
 * treb7uchet
 * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
 * <p>
 * Consider your entire calibration document. What is the sum of all of the calibration values?
 */

public class Main {
	private static int extractDigits ( String inputString ) {
		String outputString = inputString.replaceAll( "\\D+", "" );
		if ( outputString.length() < 2 ) {
			outputString += outputString;
		} else if ( outputString.length() > 2 ) {
			int n = outputString.length();
			char first = outputString.charAt( 0 );
			char last = outputString.charAt( n - 1 );
			outputString = "" + first + last;
		}
		return Integer.parseInt( outputString );
	}

	private static String replaceDigits ( String inputString ) {
		List<String> validDigits = Arrays.asList
				(
						"one", "two", "three", "four", "five", "six", "seven",
						"eight", "nine"
				);

		Map<String, String> digitMap = new HashMap<>();
		digitMap.put( "one", "1" );
		digitMap.put( "two", "2" );
		digitMap.put( "three", "3" );
		digitMap.put( "four", "4" );
		digitMap.put( "five", "5" );
		digitMap.put( "six", "6" );
		digitMap.put( "seven", "7" );
		digitMap.put( "eight", "8" );
		digitMap.put( "nine", "9" );

		StringBuilder output = new StringBuilder();

		for ( int i = 0 ; i < inputString.length() ; i++ ) {
			for ( int j = i + 1 ; j <= inputString.length() ; j++ ) {
				String substring = inputString.substring( i, j );
				if ( Character.isDigit( substring.charAt( 0 ) ) ) {
					output.append( substring );
					i = j - 1;
					break;
				} else if ( validDigits.contains( substring ) ) {
					output.append( digitMap.get( substring ) );
					i = j - 1;
					break;
				}
			}
		}

		return output.toString();
	}

	public static void main ( String[] args ) {
		int sum = 0;

		for ( String line : args ) {
			sum += extractDigits( replaceDigits( line ) );
		}
		System.out.println( sum );
	}
}