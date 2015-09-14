package temp;

import java.util.Arrays;
import java.util.Scanner;

public class InfiniteHouseOfPancakes {

	public static void main ( String [] args )
    {
    	Scanner input = new Scanner( System.in );
    	int numCases = input.nextInt();
    	
    	for( int n = 0; n < numCases; n++ )
    	{
    		int N = input.nextInt();
    		int [] pancakesInPlates = new int[ N ];
    		for( int i = 0; i < N; i++ )
    			pancakesInPlates[ i ] = input.nextInt();
    		
    		Arrays.sort( pancakesInPlates );
    		int maxNumPancakes = pancakesInPlates[ N - 1 ];
    		int min = maxNumPancakes;
    		for( int lim = 1; lim <= maxNumPancakes; lim++ )
    		{
    			int divs = 0;
    			for( int num : pancakesInPlates )
    				divs += ( num - 1 ) / lim;
    			min = Math.min( min, divs + lim );
    		}
    		
    		System.out.printf("Case #%d: %d\n", n + 1, min);
    	}
    }
}
