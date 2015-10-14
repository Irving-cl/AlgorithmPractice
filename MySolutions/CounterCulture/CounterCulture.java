import java.util.Scanner;

public class CounterCulture {

    public static void main( String [] args )
    {
        Scanner input = new Scanner( System.in );
        int numCases = input.nextInt();
        
        long [] answers = new long[ numCases ];
        for( int n = 0; n < numCases; n++ )
        {
        	boolean sign = false;
            long N = input.nextLong();
            if ( N % 10 == 0 )
            {
                N--;
                sign = true;
            }
            String stringN = String.valueOf( N );
            int kBits = stringN.length();
            long answer = countToKBits( kBits );
            
            if( kBits == 1 )
                answers[ n ] = N;
            else if( kBits == 2 )
            {
                if( stringN.charAt( 0 ) == '1' )
            	    answers[ n ] = N;
            	else
            	    answers[ n ] = countToKBits( 2 ) + N / 10 + N % 10;
            }
            else if( kBits == 3 )
            {
            	if( stringN.charAt( 0 ) == '1' )
            	    answers[ n ] = countToKBits( 3 ) + N - 100;
            	else
            	    answers[ n ] = countToKBits( 3 ) + N / 100 + N % 100;
            }
            else
            {
                answers[ n ] = countToKBits( kBits );
                for( int i = 0; i < kBits; i++ )
                	answers[ n ] += i >= kBits / 2 ?
                	( stringN.charAt( i ) - 48 ) * tenPow( kBits - i - 1 ) :		
                	( stringN.charAt( i ) - 48 ) * tenPow( i );
                if( isSpecialCase( stringN ) )
                	answers[ n ]--;
            }
            if( sign )
            	answers[ n ]++;
        }
        
        for( int n = 0; n < numCases; n++ )
        	System.out.printf( "Case #%d: %d\n", n + 1, answers[ n ] );
    }

    private static long countToKBits( int k )
    {
        if( k == 1 )
            return 0;
        else if( k == 2 )
        	return 10;
        else if( k % 2 == 1 )
        	return countToKBits( k - 1 ) + tenPow( k / 2 ) * 2 - 1;
        else
        	return countToKBits( k - 1 ) + tenPow( k / 2 - 1 ) + tenPow( k / 2 ) - 1;
    }
    
    private static long tenPow( int k )
    {
        int result = 1;
        for( int i = 0; i < k; i++ )
            result *= 10;
        return result;
    }
    
    private static boolean isSpecialCase( String s )
    {
    	if( s.charAt( 0 ) != '1')
    	    return false;
    	for( int i = 1; i < s.length() / 2; i++ )
    	    if( s.charAt( i ) != '0' )
    		    return false;
    	return true;	
    }
}

