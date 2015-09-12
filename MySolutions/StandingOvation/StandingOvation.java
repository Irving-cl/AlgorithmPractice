package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StandingOvation {

	// I\O Path
	private static final String INPUT_FILE_PATH = "D:/javaIO/A-large-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/javaIO/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/javaIO/output.txt";
		
	public static void main( String [] args ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases
		int numCases = Integer.parseInt( reader.readLine() );
		
		int answers[] = new int[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
		{
			String [] line = reader.readLine().split( " " );
			int sMax = Integer.parseInt( line[ 0 ] );
			
			int [] shynessLevelList = new int[ sMax + 1 ];
			String shynessInfo = line[ 1 ];
			for( int j = 0; j < shynessInfo.length(); j++ )
				shynessLevelList[ j ] = shynessInfo.charAt( j ) - 48;
			
			int numFriends = 0, numStandAudiences = 0, curShynessLevel = 0;
			while( curShynessLevel <= sMax )
			{
				while( shynessLevelList[ curShynessLevel ] == 0 ) 
					curShynessLevel++;

				if( numStandAudiences < curShynessLevel )
				{
					numFriends += ( curShynessLevel - numStandAudiences );
					numStandAudiences = curShynessLevel + shynessLevelList[ curShynessLevel++ ];
				}
				else
					numStandAudiences += shynessLevelList[ curShynessLevel++ ];
				
			}
			
			answers[ i ] = numFriends;
		}
		
		// Output the result
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
