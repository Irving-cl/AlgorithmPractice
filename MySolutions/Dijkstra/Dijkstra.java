package com.main;

import java.util.Scanner;

public class Dijkstra {

	public static void main( String ... orange )
	{
		Scanner input = new Scanner( System.in );
		int numCases = Integer.parseInt( input.nextLine() );
		
		Boolean [] answers = new Boolean[ numCases ];
		
		for( int n = 0; n < numCases; n++ )
		{
			String [] metadata = input.nextLine().split( " " );
			int X = Integer.parseInt( metadata[ 0 ] );
			long L = Long.parseLong( metadata[ 1 ] );
			String spelling = input.nextLine();
			int [] array = new int[ spelling.length() ];
			for( int i = 0; i < spelling.length(); i++ )
				array[ i ] = spelling.charAt( i ) - 103;
			
			int valueOneLoop = computeValue( array, 0, array.length - 1 );
			boolean isPossible = false, result = false;
			if( valueOneLoop == -1 )
			{
				if( L % 2 == 1 )
					isPossible = true;
			}
			else if( valueOneLoop != 1 )
				if( L % 4 == 2 )
					isPossible = true;
			
			if( isPossible && X * L > 2 )
			{
				int [] fourArray = new int[ 4 * array.length ];
				for( int i = 0; i < 4; i++ )
					for( int j = 0; j < array.length; j++ )
						fourArray[ i * array.length + j ] = array[ j ];
				
				long limit = L < 4 ? ( X * L - 1 ) : ( X * 4 - 1 );
				int left = 1;
				for( int i = 0; i < limit; i++ )
				{
					left = starMultiply( left, fourArray[ i ] );
					if( left == I )
					{
						int right = 1, counter = 0;
				        for( int j = fourArray.length - 1; L * X - ( ++counter ) > i + 1; j-- )
				        {
				        	if( counter > limit )
				        		break;
				        	right = starMultiply( fourArray[ j ], right );
				        	if( right == K )
				        	{
				        		result = true;
				        		break;
				        	}
				        }
				        if( result ) break;
					}
				}
			}
			else
				result = false;
			
			answers[ n ] = result;
		}
		
		for( int n = 0; n < numCases; n++ )
		{
			String isWork = answers[ n ] ? "Yes" : "NO";
			System.out.printf( "Case #%d: %s\n", n + 1, isWork );
		}
		
	}
	
	// Internal methods
	private static int computeValue( int [] spelling, int left, int right )
	{
		if( left == right )
			return spelling[ left ];
		else
		{
			int middle = ( left + right ) / 2;
			int leftPart = computeValue( spelling, left, middle );
			int rightPart = computeValue( spelling, middle + 1, right );
			return starMultiply( leftPart, rightPart );
		}
	}
	
	private static int starMultiply( int a, int b )
	{
		if( a < 0 )
			return -starMultiply( -a, b );
		else if( b < 0 )
            return -starMultiply( a, -b );
		else 
			return actualNumberSystem[ a - 1 ][ b - 1 ];
	}
	
	// Constants
	private static final int ONE = 1;
	private static final int I = 2;
	private static final int J = 3;
	private static final int K = 4;
	
	// static variable
	private static final int [][] actualNumberSystem = { { ONE, I, J, K }, { I, -ONE, K, -J }, 
		{ J, -K, -ONE, I }, { K, J, -I, -ONE } };
	
}
