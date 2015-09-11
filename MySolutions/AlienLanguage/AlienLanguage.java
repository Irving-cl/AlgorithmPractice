package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AlienLanguage {

	// Member variables
	private static final String INPUT_FILE_PATH = "D:/JavaIO/A-large-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/JavaIO/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/JavaIO/output.txt";
		
	private static int [] answers;
	
	private static String [] CombinationSource( int wordLength, String input )
	{
		String [] source = new String[ wordLength ];
		int index = 0;
		int kth = 0;
		while( index < input.length() )
		{
			if( input.charAt( index ) == '(' )
			{
				int start = index + 1;
				while( input.charAt( ++index ) != ')' ) { }
				source[ kth ] = input.substring( start, index );
			}
			else
				source[ kth ] = "" + input.charAt( index );
			index++;
			kth++;
		}
		
		return source;
	}
	
	private static int Combination( String tmp, String [] source, int wordLength, Set<String> alienWords )
	{
		if( tmp.length() == wordLength )
			return alienWords.contains( tmp ) ? 1 : 0;
		else
		{
			int curIndex = tmp.length();
			int num = 0;
			for( int i = 0; i < source[ curIndex ].length(); i++ )
				num += Combination( tmp + source[ curIndex ].charAt( i ), source, wordLength, alienWords );
			return num;
		}
				
	}
	
	private static int possibleWords( String [] source, Set<String> alienWords )
	{
		int numPossible = 0;
		for( String word : alienWords )
		{
			int numOk = 0;
			for( int i = 0; i < word.length(); i++ )
				if( source[ i ].indexOf( word.charAt( i ) ) != -1 )
					numOk++;
			if( numOk == source.length )
			    numPossible++;
		}
		return numPossible;	
	}
	
	public static void main( String [] args ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		String [] metadata = reader.readLine().split( " " );
		int wordLength = Integer.parseInt( metadata[ 0 ] );
		int numTotalWords = Integer.parseInt( metadata[ 1 ] );
		int numCases = Integer.parseInt( metadata[ 2 ] ); 
		
		Set<String> alienWords = new HashSet<String>();
		for( int i = 0; i < numTotalWords; i++ )
			alienWords.add( reader.readLine() );
		
		long startMili = System.currentTimeMillis();
		answers = new int[ numCases ];
		/* Solution verion1
		for( int i = 0; i < numCases; i++ )
		{
			String [] source = CombinationSource( wordLength, reader.readLine() );
            answers[ i ] = Combination( "", source, wordLength, alienWords );
		}
		*/
		// Solution version2
		for( int i = 0; i < numCases; i++ )
		{
			String [] source = CombinationSource( wordLength, reader.readLine() );
			answers[ i ] = possibleWords( source, alienWords );
		}
		long endMili = System.currentTimeMillis();
		System.out.println("Time:" + (endMili - startMili) + "ms");
		
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
