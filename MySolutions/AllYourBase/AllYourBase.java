package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllYourBase {

	// I\O Path
	private static final String INPUT_FILE_PATH = "D:/javaIO/A-large-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/javaIO/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/javaIO/output.txt";
	
	public static void main( String [] args ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		
		long answers[] = new long[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
		{
			String warSecondsCrypted = reader.readLine();
			Set<Character> diffChars = new HashSet<Character>();
			for( int j = 0; j < warSecondsCrypted.length(); j++ )
				if( !diffChars.contains( warSecondsCrypted.charAt( j ) ) )
					diffChars.add( warSecondsCrypted.charAt( j ) );
			int base = diffChars.size() < 2 ? 2 : diffChars.size();

			Map<Character, Integer> symbolTable = new HashMap<Character, Integer>();
			
			int [] distributeDigits = new int[ base ];
			distributeDigits[ 0 ] = 1;
			distributeDigits[ 1 ] = 0;
			for( int j = 2; j < base; j++ )
				distributeDigits[ j ] = j;
			//System.out.println( warSecondsCrypted );
			for( int j = 0, k = 0; j < warSecondsCrypted.length(); j++ )
			{
				Character thisChar = warSecondsCrypted.charAt( j );
				if( !symbolTable.containsKey( thisChar ) )
					symbolTable.put( thisChar, distributeDigits[ k++ ] );
			}
			
			long warSeconds = 0;
			for( int j = 0; j < warSecondsCrypted.length(); j++ )
			{
				warSeconds *= base;
				warSeconds += symbolTable.get( warSecondsCrypted.charAt( j ) );
			}
			
			answers[ i ] = warSeconds;
		}
		
		FileOutputStream out = new FileOutputStream( new File( OUTPUT_FILE_PATH ) );
		String line = "";
		
		for( int i = 0; i < answers.length; i++ )
		{
			line = "Case #" + ( i + 1 ) + ": " + answers[ i ] + "\r\n";
		    out.write( line.getBytes() );
		}
		
		out.close();
	}
	
}

