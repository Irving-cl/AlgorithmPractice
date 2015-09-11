package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseWords {

	private static void readInputFile( String filePath ) throws IOException
	{
		File file = new File( filePath );
		InputStreamReader read = new InputStreamReader( new FileInputStream( file ) );
		BufferedReader reader = new BufferedReader( read );
		
		// Read in the number of cases
		int numCases = Integer.parseInt( reader.readLine() );
		
		// Read in cases
		cases = new String[ numCases ];
		for( int i = 0; i < numCases; i++ )
			cases[ i ] = reader.readLine();
		
	}
	
	private static void generateSolution()
	{
		answers = new String[ cases.length ];
		for( int i = 0; i < cases.length; i++ )
		{
			String [] words = cases[ i ].split( " " );
			answers[ i ] = "";
			for( int j = words.length - 1; j > -1; j--)
				answers[ i ] += ( " " + words[j] );
		}
	}
	
	private static void writeAnswerToFile( String filePath ) throws IOException
	{
		FileOutputStream out = new FileOutputStream( new File( filePath ) );
	    for( int i = 0; i < answers.length; i++ )
	    {
	    	String line = "Case #" + ( i + 1 ) + ":" + answers[ i ] + "\r\n";
	    	out.write( line.getBytes() );
	    }
	    out.close();
	}
	
	private static String [] cases;
	private static String [] answers;
	
	private static final String INPUT_FILE_PATH = "D:/javaIO/B-large-practice.in";
	//private static final String INPUT_FILE_PATH = "src/com/res/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/javaIO/output.txt";
	
	public static void main( String [] args ) throws IOException
	{
		readInputFile( INPUT_FILE_PATH );
		generateSolution();
		writeAnswerToFile( OUTPUT_FILE_PATH );
	}
	
}
