package com.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class StoreCredit {

	private static void readInputFile( String filePath ) throws IOException
	{
		File file = new File( filePath );
		InputStreamReader read = new InputStreamReader( new FileInputStream( file ) );
		BufferedReader bufferedReader = new BufferedReader( read );
		
		// Read in number of cases
		int numCases = Integer.parseInt( bufferedReader.readLine() );
		
		// Read in every case
		cases = new StoreCreditClass[ numCases ];
		for( int i = 0; i < numCases; i++ )
		{
			int credit = Integer.parseInt( bufferedReader.readLine() );
			int numItems = Integer.parseInt( bufferedReader.readLine() );
			String [] prices = bufferedReader.readLine().split(" ");
			cases[ i ] = new StoreCreditClass( credit, numItems, prices );
		}
		
	}
	
	private static void generateSolution()
	{
		answers = new StoreCreditAnswer[ cases.length ];
		
		for( int i = 0; i < cases.length; i++ )
		{
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int credit = cases[ i ].credit;
			int [] prices = cases[ i ].prices;
			int thisPrice;
			map.put( prices[ 0 ], 1 );
			for( int j = 1; ; j++ )
			{
				thisPrice = prices[ j ];
				if( thisPrice < credit )
				{
					if( map.containsKey( credit - thisPrice ) )
					{
						answers[ i ] = new StoreCreditAnswer( map.get( credit - thisPrice ), j + 1 );
						break;
					}
					else
						map.put( thisPrice, j + 1 );
				}
			}
		}
	}
	
	private static void writeAnswerToFile() throws IOException
	{
		FileOutputStream out = new FileOutputStream(new File(OUTPUT_FILE_PATH));
		String line;
		
		for( int i = 0; i < answers.length; i++ )
		{
			int small = answers[ i ].index1 < answers[ i ].index2 ? answers[ i ].index1 : answers[ i ].index2;
			int big = small == answers[ i ].index1 ? answers[ i ].index2 : answers[ i ].index1;
			int k = i + 1;
			line = "Case #" + k + ": " + small + " " + big + "\r\n";
			out.write( line.getBytes() );
		}
		
		out.close();
	}
	
	// Nested class
	private static class StoreCreditClass
	{
		StoreCreditClass( int c, int n, String [] p )
		{
			credit = c;
			numItems = n;
			prices = new int[ numItems ];
			for( int i = 0; i < numItems; i++ )
				prices[ i ] = Integer.valueOf( p[ i ] );
		}
		
		int credit;
		int numItems;
		int [] prices;
	}
	
	private static class StoreCreditAnswer
	{
		StoreCreditAnswer( int i1, int i2 )
		{
			index1 = i1;
			index2 = i2;
		}
		
		int index1, index2;
	}
	
	// Member variables
	private static final String INPUT_FILE_PATH = "D:/javaIO/A-large-practice.in";
	private static final String OUTPUT_FILE_PATH = "D:/javaIO/output.txt";
	private static StoreCreditClass [] cases;
	private static StoreCreditAnswer [] answers;
	
	public static void main( String [] args ) throws IOException
	{
		readInputFile( INPUT_FILE_PATH );
		/*for( StoreCreditClass onecase : cases )
		{
			System.out.println( onecase.credit );
			System.out.println( onecase.numItems );
			int [] tmp = onecase.prices;
			for( int i = 0; i < tmp.length; i++ )
				System.out.print( tmp[ i ] + " " );
			System.out.println();	
		}*/
		generateSolution();
		/*for( StoreCreditAnswer answer : answers )
		{
			System.out.print( answer.index1 + " " + answer.index2);
			System.out.println();
		}*/
		writeAnswerToFile();
	}
}
