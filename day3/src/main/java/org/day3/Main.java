package org.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 3: Gear Ratios ---
 * You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.
 * <p>
 * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
 * <p>
 * "Aaah!"
 * <p>
 * You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
 * <p>
 * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
 * <p>
 * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
 * <p>
 * Here is an example engine schematic:
 * <p>
 * 467..114..<br/>
 * ...*......<br/>
 * ..35..633.<br/>
 * ......#...<br/>
 * 617*......<br/>
 * .....+.58.<br/>
 * ..592.....<br/>
 * ......755.<br/>
 * ...$.*....<br/>
 * .664.598..<br/>
 * In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
 * <p>
 * Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?
 */
public class Main {

	public static void main ( String[] args ) {
		part2( args );
	}

	private static void part2 (String[] args) {
		char[][] grid = new char[args.length][];
		for (int i = 0; i < args.length; i++) {
			grid[i] = args[i].toCharArray();
		}

		Map<String, List<Integer>> gearNumbers = new HashMap<>();

		for (int y = 0; y < grid.length; y++) {
			String currentNumber = "";
			boolean checkNumber = false;
			String gearLocation = null;

			for (int x = 0; x < grid[y].length; x++) {
				if (Character.isDigit(grid[y][x]) && !checkNumber) {
					checkNumber = true;
					currentNumber = "";
					gearLocation = null;
				}

				if ((x == grid[y].length - 1 || !Character.isDigit(grid[y][x])) && checkNumber) {
					if (gearLocation != null) {
						gearNumbers.get(gearLocation).add(Integer.parseInt(currentNumber + ((Character.isDigit(grid[y][x])) ? String.valueOf(grid[y][x]) : "")));
					}
					checkNumber = false;
				}

				if (checkNumber) {
					currentNumber += grid[y][x];

					for (int j = -1; j <= 1; j++) {
						for (int i = -1; i <= 1; i++) {
							if (i == 0 && j == 0) continue;
							if (y + j < 0 || y + j >= grid.length || x + i < 0 || x + i >= grid[y].length) continue;

							char charAt = grid[y + j][x + i];
							if (charAt == '*') {
								gearLocation = (x + i) + "," + (y + j);
								if (!gearNumbers.containsKey(gearLocation)) {
									gearNumbers.put(gearLocation, new ArrayList<>());
								}
							}
						}
					}
				}
			}
		}

		int sum = 0;
		for (List<Integer> list : gearNumbers.values()) {
			if (list.size() == 2) {
				sum += list.get(0) * list.get(1);
			}
		}
		System.out.println(sum);
	}

	private static void part1 ( String[] args ) {
		List<char[]> grid = new ArrayList<>();
		for (String line : args ) {
			grid.add(line.toCharArray());
		}
		int sum = 0;
		for (int y = 0; y < grid.size(); y++) {
			String currentNumber = "";
			boolean checkNumber = false;
			boolean nearSymbol = false;
			for (int x = 0; x < grid.get(y).length; x++) {
				// if current spot is a number and we aren't checking them yet, start checking
				if (Character.isDigit(grid.get(y)[x]) && !checkNumber) {
					checkNumber = true;
					currentNumber = "";
					nearSymbol = false;
				}
				// if we find a non-number or at end of the row, stop checking and add to sum if needed
				if ((x == grid.get(y).length - 1 || !Character.isDigit(grid.get(y)[x])) && checkNumber) {
					if (nearSymbol) {
						sum += Integer.parseInt(currentNumber + ((Character.isDigit(grid.get(y)[x])) ? grid.get(y)[x] : ""));
					}
					checkNumber = false;
				}
				// if we are checking for numbers, add current spot to number and check for symbols around it
				if (checkNumber) {
					currentNumber += grid.get(y)[x];
					// check for symbol around current spot
					for (int j = -1; j <= 1; j++) {
						for (int i = -1; i <= 1; i++) {
							if (i == 0 && j == 0) {
								continue;
							}
							if (y + j < 0 || y + j >= grid.size() || x + i < 0 || x + i >= grid.get(y).length) {
								continue;
							}
							// anything that is not a number or . is a symbol
							if (!Character.isDigit(grid.get(y + j)[x + i]) && grid.get(y + j)[x + i] != '.') {
								nearSymbol = true;
							}
						}
					}
				}
			}
		}
		System.out.println(sum);
	}
}