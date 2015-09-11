package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MinScalarProduct {
	
	// Quick Sort
	private static final int CUTOFF = 10;
		
	private static <AnyType extends Comparable<? super AnyType>>
	void swapReference( AnyType [] a, int i, int j ) 
	{
		AnyType tmp = a[ i ];
		a[ i ] = a[ j ];
		a[ j ] = tmp;
	}
		
	private static <AnyType extends Comparable<? super AnyType>>
	void insertionSort( AnyType [] a, int left, int right )
	{
		int j;
			
		for( int i = left + 1; i < a.length; i++ )
		{
			AnyType tmp = a[i];
			for( j = i; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
			    a[ j ] = a[ j - 1 ];
			a[ j ] = tmp;
		}
			    
    }
		
    public static <AnyType extends Comparable<? super AnyType>>
	void quickSort( AnyType [] a )
	{
		quickSort( a, 0, a.length - 1 ); 
	}
		
	private static <AnyType extends Comparable<? super AnyType>>
	void quickSort( AnyType [] a, int left, int right ) 
	{
		if( left + CUTOFF <= right )
		{
			AnyType pivot = median3( a, left, right );
				
			int i = left, j = right - 1;
			for( ; ; )
			{
				while( a[ ++i ].compareTo( pivot ) < 0 ) { }
				while( a[ --j ].compareTo( pivot ) > 0 ) { }
				if( i < j )
					swapReference( a, i, j );
				else
					break;
			}
				
			swapReference( a, i, right - 1 );
				
		    quickSort( a, left, i - 1 );
			quickSort( a, i + 1, right);
		}
		else
			insertionSort( a, left, right );
			
	}
		
	private static <AnyType extends Comparable<? super AnyType>>
	AnyType median3( AnyType [] a, int left, int right )
	{
		int center = ( left + right ) / 2;
			
		if( a[ center ].compareTo(a[ left ]) < 0 )
			swapReference( a, left, center );
		if( a[ right ].compareTo(a[ left ]) < 0 )
			swapReference( a, left, right );
		if( a[ right ].compareTo(a[ center]) < 0 )
			swapReference( a, center, right );
			
		swapReference( a, center, right - 1 );
		return a[ right - 1 ];
	}
		
	// Scalar product
	private static Long minScalarProduct( Long [] v1, Long [] v2 )
	{
		Long result = (long) 0;
	    for( int i = 0; i < v1.length; i++ )
	    	result += v1[ i ] * v2[ v1.length - 1 - i ];
	    return result;
	}
	
	// Member variables
	private static final String INPUT_FILE_PATH = "D:/JavaIO/A-large-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/JavaIO/C-small-practice.in";
	private static final String OUTPUT_FILE_PATH = "D:/JavaIO/output.txt";
	
    public static void main( String [] args ) throws NumberFormatException, IOException
    {
    	File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
			
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		
		Long [] answers = new Long[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
		{
			reader.readLine();
			String [] line1 = reader.readLine().split( " " );
			String [] line2 = reader.readLine().split( " " );
			Long [] v1 = new Long[ line1.length ];
			Long [] v2 = new Long[ line2.length ];
			for( int j = 0; j < line1.length; j++ )
			{
				v1[ j ] = Long.parseLong( line1[ j ] );
				v2[ j ] = Long.parseLong( line2[ j ] );
			}
			
			quickSort( v1 );
			quickSort( v2 );
			
			answers[ i ] = minScalarProduct( v1, v2 );
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
