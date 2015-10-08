
import java.util.Scanner;

public class Haircut {

    public static void main( String [] args )
    {
        Scanner input = new Scanner( System.in );
        int numCases = Integer.parseInt( input.nextLine() );
        
        long [] answers = new long[ numCases ];
        
        for( int n = 0; n < numCases; n++ )
        {
            String [] metadata = input.nextLine().split( " " );
            long B = Long.parseLong( metadata[ 0 ] );
            long N = Long.parseLong( metadata[ 1 ] );
   
            int [] barbers = new int[ (int) B ];
		    String [] bbs = input.nextLine().split( " " );
            for( int i = 0; i < B; i++ )
                barbers[ i ] = Integer.parseInt( bbs[ i ] );
		    
		    // Find out time
		    long time = 1;
            for( ; ; )
		    {
                if( sum( time, barbers ) >= N )
		        	break;
                time = time * 2;
            }
            long exactTime = findOutTime( time / 2, time, barbers, N );
		    
		    // Find out the barber
            long kthInMinute = N - sum( exactTime - 1, barbers );
            int i = -1, j = 0;
            for( ; ; )
            {
                while( exactTime % barbers[ ++i ] != 0 ) {}
                if( ++j == kthInMinute )
                   break;
            }
            answers[ n ] = i + 1;
        }
        
        for( int n = 0; n < numCases; n++ )
            System.out.printf( "Case #%d: %d\n", n + 1, answers[ n ] );
        
    }
    
    private static long findOutTime( long start, long end, int [] barbers, long n )
    {
    	if( start == end )
    		return start;
    	long middle = ( start + end ) / 2;
    	if( sum( middle, barbers ) < n )
    		return findOutTime( middle + 1, end, barbers, n );
    	else
    		return findOutTime( start, middle, barbers, n );
    }

    private static long sum( long time, int [] barbers )
    {
        long sum = barbers.length;
        for( int i = 0; i < barbers.length; i++ )
            sum += time / barbers[ i ];
        return sum;
    }
}
