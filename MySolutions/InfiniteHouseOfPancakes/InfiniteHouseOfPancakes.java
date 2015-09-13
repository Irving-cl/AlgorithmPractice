package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class InfiniteHouseOfPancakes {

	// Reverse comparator of Integer
	private static Comparator<Integer> reverseInteger = new Comparator<Integer>()
	{
		public int compare( Integer o1, Integer o2 ) {
			if( o1 < o2 )
				return 1;
			else if( o1 > o2 )
				return -1;
			else
			    return 0;
		}	
	};
	
    // Internal method
	private static int isSpecialMinuteMeaningful( PriorityQueue<Integer> pancakesInPlates )
	{
		int mostPancakes = pancakesInPlates.poll();
		int numMostPancakes = 1;
		while( !pancakesInPlates.isEmpty() && pancakesInPlates.peek() == mostPancakes )
		{
			pancakesInPlates.poll();
			numMostPancakes++;
		}
		
		return  ( mostPancakes / 2 ) > numMostPancakes ? numMostPancakes : -1;
	}
	
	// I\O Path
	private static final String INPUT_FILE_PATH = "D:/javaIO/B-small-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/javaIO/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/javaIO/output.txt";
		
	public static void main( String args [] ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		
		int answers[] = new int[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
		{
		    int numPlates = Integer.parseInt( reader.readLine() );
		    String [] numPancakesList = reader.readLine().split( " " );
		    
			PriorityQueue<Integer> pancakesInPlates = new PriorityQueue<Integer>( reverseInteger );
			for( int j = 0; j < numPlates; j++ )
				pancakesInPlates.add( Integer.parseInt( numPancakesList[ j ] ) );
			
			int totalMinutes = 0;
			int curMostPancakes = 0;
			while( ( curMostPancakes = pancakesInPlates.peek() ) != 0 )
			{
				int checkResult = isSpecialMinuteMeaningful( pancakesInPlates );
				if( checkResult > 0 )
				{
					for( int j = 0; j < checkResult; j++ )
					{
						pancakesInPlates.add( curMostPancakes / 2 );
						pancakesInPlates.add( curMostPancakes - curMostPancakes / 2 );
					}
					totalMinutes += checkResult;
				}
				else
				{
					totalMinutes += curMostPancakes;
					break;
				}
			}
			System.out.println( "Answer: " + totalMinutes );
			answers[ i ] = totalMinutes;
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
