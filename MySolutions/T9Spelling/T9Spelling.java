package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class T9Spelling {

	// Internal methods
	private static void readInputFile() throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		messages = new String[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
			messages[ i ] = reader.readLine();
		
		reader.close();
	}
	
	private static void getAnswers()
	{
		// New answers
		answers = new String[ messages.length ];
		
		for( int i = 0; i < answers.length; i++ )
		{
			String msg = messages[ i ];
			answers[ i ] = "";
			for( int j = 0; j < msg.length(); j++ )
			{
				String seq = mapCharToSeq( msg.charAt( j ) );
				if( !answers[ i ].isEmpty() &&
						answers[ i ].charAt( answers[ i ].length() - 1 ) == seq.charAt( 0 ) )
					answers[ i ] += " ";
				answers[ i ] += seq;
			}
		}
	}
	
	private static String mapCharToSeq( char c )
	{
		// Case for space
		if( c == ' ' )
			return String.valueOf( 0 );
		
		// Case for 'a' - 'z'
		String seq = "";
		int times;
		String keyToPress;
		int alphabetOrder = c - 'a';
		if( alphabetOrder < 15)
		{
		    times = alphabetOrder % 3 + 1;
		    keyToPress = String.valueOf( alphabetOrder / 3 + 2 );
		}
		else if( alphabetOrder < 19 )
		{
			times = alphabetOrder - 14;
			keyToPress = String.valueOf(7);
		}
		else if( alphabetOrder < 22 )
		{
			times = alphabetOrder - 18;
			keyToPress = String.valueOf(8);
		}
		else
		{
			times = alphabetOrder - 21;
			keyToPress = String.valueOf(9);
		}
		
		for( int i = 0; i < times; i++ )
			seq += keyToPress;
		
		return seq;
	}
	
	private static void writeAnswerToFile() throws IOException
	{
		FileOutputStream out = new FileOutputStream( new File( OUTPUT_FILE_PATH ) );
		String line = "";
		
		for( int i = 0; i < answers.length; i++ )
		{
			line = "Case #" + ( i + 1 ) + ": " + answers[ i ] + "\r\n";
		    out.write( line.getBytes() );
		}
		
		out.close();
	}
	
	// Member variables
	private static final String INPUT_FILE_PATH = "D:/JavaIO/C-large-practice.in";
	private static final String OUTPUT_FILE_PATH = "D:/JavaIO/output.txt";
	
	private static String [] messages;
	private static String [] answers;
	
	// Main 
	public static void main( String [] args ) throws NumberFormatException, IOException
	{
		readInputFile();
		getAnswers();
		writeAnswerToFile();
	}
}
