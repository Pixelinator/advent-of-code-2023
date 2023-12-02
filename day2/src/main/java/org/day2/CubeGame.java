package org.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CubeGame {

	int id = -1;
	int numberOfRedCubes = 0;
	int numberOfGreenCubes = 0;
	int numberOfBlueCubes = 0;
	boolean possible = false;

	public CubeGame ( String gameInfo ) {
		this.parseGameInfo( gameInfo );
	}

	public boolean isPossible ( int redCubes, int greenCubes, int blueCubes ) {
		this.possible = this.numberOfRedCubes <= redCubes && this.numberOfGreenCubes <= greenCubes && this.numberOfBlueCubes <= blueCubes;
		return this.possible;
	}

	public int getId () {
		return id;
	}

	private void parseGameInfo ( String gameInfo ) {
		String[] idSplit = gameInfo.split( ":" );
		String[] gameSplit = idSplit[ 1 ].split( ";" );
		ArrayList<String[]> cubeSplits = new ArrayList<>();
		for ( int i = 0 ; i < gameSplit.length ; i++ ) {
			cubeSplits.add( gameSplit[ i ].split( "," ) );
		}
		cubeSplits.forEach( cubeSplit -> {
			for ( int i = 0 ; i < cubeSplit.length ; i++ ) {
				String[] split = cubeSplit[ i ].split( "" );
				parseNumberOfCubesByColor( split );
			}
		} );
		this.id = findIntegers( idSplit[ 0 ] ).get( 0 );
	}

	private void parseNumberOfCubesByColor ( String[] split ) {
		String wholeSplit = Arrays.stream( Arrays.copyOfRange( split, 0, split.length ) ).collect( Collectors.joining() );
		Integer numberOfCubes = findIntegers( wholeSplit ).get( 0 );
		String cubeColor = wholeSplit.replaceAll( numberOfCubes.toString(), "" );
		if ( Objects.equals( cubeColor, "red" ) ) {
			this.numberOfRedCubes = numberOfCubes > this.numberOfRedCubes ? numberOfCubes : this.numberOfRedCubes;
		}
		if ( Objects.equals( cubeColor, "green" ) ) {
			this.numberOfGreenCubes = numberOfCubes > this.numberOfGreenCubes ? numberOfCubes : this.numberOfGreenCubes;
		}
		if ( Objects.equals( cubeColor, "blue" ) ) {
			this.numberOfBlueCubes = numberOfCubes > this.numberOfBlueCubes ? numberOfCubes : this.numberOfBlueCubes;
		}
	}

	private List<Integer> findIntegers ( String stringToSearch ) {
		Pattern integerPattern = Pattern.compile( "-?\\d+" );
		Matcher matcher = integerPattern.matcher( stringToSearch );

		List<Integer> integerList = new ArrayList<>();
		while ( matcher.find() ) {
			integerList.add( Integer.valueOf( matcher.group() ) );
		}

		return integerList;
	}
}
