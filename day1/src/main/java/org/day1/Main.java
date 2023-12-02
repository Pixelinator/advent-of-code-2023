package org.day1;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {
		Path filePath = Path.of("day1/src/main/resources/d1.txt");

		try ( BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
			String line;
			int sum = 0;

			while ((line = reader.readLine()) != null) {
				//replace lettered numbers with digital number
				line = replaceLetteredNumbers(line);
				//replace all non-digit chars
				String intValue = line.replaceAll("[^0-9]", "");

				char[] charArray = intValue.toCharArray();
				String firstAndLast = "" + charArray[0] + charArray[charArray.length - 1];
				sum += Integer.parseInt(firstAndLast);
			}

			System.out.println("SUM: " + sum);

		} catch ( IOException e) {
			e.printStackTrace();
		}
	}

	/* Day 1 Part 2 */
	private static String replaceLetteredNumbers(String line) {
		String[] searchList = new String[]{"oneight", "twone", "eightwo", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] replacementList = new String[]{"18", "21", "82", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		return StringUtils.replaceEach(line, searchList, replacementList);
	}
}
