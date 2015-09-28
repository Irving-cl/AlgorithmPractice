import java.util.Scanner;

public class MushroomMonster {

    public static void main( String [] args )
    {
        Scanner input = new Scanner( System.in );
        int numCases = Integer.parseInt( input.nextLine() );
    
        int [] answersM1 = new int[ numCases ];
        int [] answersM2 = new int[ numCases ];
    
        for( int n = 0; n < numCases; n++ )
        {
            int numPoints = Integer.parseInt( input.nextLine() );
            String [] temp = input.nextLine().split( " " );
            int [] timePoints = new int[ numPoints ];
            for( int i = 0; i < numPoints; i++ )
            	timePoints[ i ] = Integer.parseInt( temp[ i ] );
            
            int eatingRate = 0, answerM1 = 0, answerM2 = 0;
            for( int i = 0; i < numPoints - 1; i++ )
            {
            	int gap = timePoints[ i ] - timePoints[ i + 1 ];
            	if( gap > 0 )
            	{
            		answerM1 += gap;
            		eatingRate = gap > eatingRate ? gap : eatingRate; 
            	}
            }
            answersM1[ n ] = answerM1;
            
            for( int i = 0; i < numPoints - 1; i++ )
                answerM2 += eatingRate > timePoints[ i ] ? timePoints[ i ] : eatingRate;
            answersM2[ n ] = answerM2;
        }
        
        for( int n = 0; n < numCases; n++ )
			System.out.printf( "Case #%d: %d %d\n", n + 1, answersM1[ n ], answersM2[ n ] );
        
    }
	
}
