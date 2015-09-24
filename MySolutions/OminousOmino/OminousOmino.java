import java.util.Scanner;

public class OminousOmino 
{
    public static void main( String [] args )
    {
	Scanner input = new Scanner( System.in );
	int numCases = input.nextInt();
		
	boolean [] answers = new boolean[ numCases ];
	for( int n = 0; n < numCases; n++ )
	{
		int X = input.nextInt();
		int R = input.nextInt();
		int C = input.nextInt();
		answers[ n ] = RichardWins( X, Math.max( R, C ), Math.min( R, C ) );
	}
	
	for( int n = 0; n < numCases; n++ )
	{
		String winner = answers[ n ] ? "RICHARD" : "GABRIEL";
		System.out.printf( "Case #%d: %s\n", n + 1, winner );
	}
			
    }
	
    private static boolean RichardWins( int X, int R, int C )
    {
    	if( R * C % X != 0 || R < X )
    		return true;
    	if( X > 6 )
    		return true;
    	if( X < 3 )
    		return false;
    	
    	if( maxMinLength( X ) > C )
    		return true;
    	else if( maxMinLength( X ) < C )
    		return false;
    	else
    	{
    		if( X == 3 )
    			return false;
    		else if( X == 5 )
    		{
    			if( R > 5 )
    				return false;
    			else
    				return true;
    		}
    		else
    			return true;
    	}
    }
	
    private static int maxMinLength( int X )
    {
    	return X % 2 == 0 ? X / 2 : X / 2 + 1;
    }
    
}
