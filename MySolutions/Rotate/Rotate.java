package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Rotate {
  
	public enum Cell
	{
		RED, BLUE, EMPTY;
	}
	
	private static class Result
	{
		Result()
		{
			Red = false;
			Blue = false;
		}
		
		boolean Red;
		boolean Blue;
	}
	
	// Internal method
	private static void rightGravityMatrix( Cell [][] matrix )
	{
		for( int i = 0; i < matrix.length; i++ )
			rightGravityLine( matrix[ i ] );
	}
	
	private static void rightGravityLine( Cell [] line )
	{
		Stack<Cell> stack = new Stack<Cell>();
		for( int i = 0; i < line.length; i++ )
		    if( line[ i ] != Cell.EMPTY )
		    	stack.push( line[ i ] );
			
		int criticalIndex = line.length - stack.size() - 1;
		for( int j = line.length - 1; j > criticalIndex; j-- )
			line[ j ] = stack.pop();
		for( int j = criticalIndex; j > -1; j-- )
			line[ j ] = Cell.EMPTY;
		
	}
	
	private static void checkLineRight( Cell [] line, int k, Result result )
	{
		int firstNotEmptyIndex = 0;
		while( firstNotEmptyIndex < line.length )
			if( line[ firstNotEmptyIndex++ ] != Cell.EMPTY )
			{
				firstNotEmptyIndex--;
				break;
			}
		
		Cell lastCell = Cell.EMPTY;
		int curSeqNum = 0, maxSeqNumRed = 0, maxSeqNumBlue = 0;
		for( int i = firstNotEmptyIndex; i < line.length; i++ )
		{
			Cell thisCell = line[ i ];
			if( thisCell == lastCell )
				curSeqNum++;
			else
				curSeqNum = 1;
			
			if( thisCell == Cell.RED )
				maxSeqNumRed = curSeqNum > maxSeqNumRed ? curSeqNum : maxSeqNumRed;
			else
				maxSeqNumBlue = curSeqNum > maxSeqNumBlue ? curSeqNum : maxSeqNumBlue;	
				
			lastCell = thisCell;
		}

		if( !result.Red )
		    result.Red = maxSeqNumRed >= k;
		if( !result.Blue)
		    result.Blue = maxSeqNumBlue >= k;

	}
	
	private static void checkLineLeft( Cell [] line, int k, Result result )
	{
		int firstEmptyIndex = 0;
		while( firstEmptyIndex < line.length )
			if( line[ firstEmptyIndex ] == Cell.EMPTY )
				break;
			else 
				firstEmptyIndex++;
		
		Cell lastCell = Cell.EMPTY;
		int curSeqNum = 0, maxSeqNumRed = 0, maxSeqNumBlue = 0;
		for( int i = 0; i < firstEmptyIndex; i++ )
		{
			Cell thisCell = line[ i ];
			if( thisCell == lastCell )
				curSeqNum++;
			else
				curSeqNum = 1;
			
			if( thisCell == Cell.RED )
				maxSeqNumRed = curSeqNum > maxSeqNumRed ? curSeqNum : maxSeqNumRed;
			else
				maxSeqNumBlue = curSeqNum > maxSeqNumBlue ? curSeqNum : maxSeqNumBlue;	
				
			lastCell = thisCell;
		}
			
		if( !result.Red )
		    result.Red = maxSeqNumRed >= k;
		if( !result.Blue)
		    result.Blue = maxSeqNumBlue >= k;
		    
	}
	
	private static void checkDiagnal( Cell [][] matrix, int k, int m, int n, Result result )
	{
		Stack<Cell> leftDiagnal = new Stack<Cell>();
		Stack<Cell> rightDiagnal = new Stack<Cell>();
		
		int i = m, j = n, N = matrix.length;
		while( i < N && j > -1 )
		{
		    Cell thisCell = matrix[ i++ ][ j-- ];
			if( thisCell != Cell.EMPTY )
				leftDiagnal.push( thisCell );
		}
		
		i = m;
		j = n;
		while( i < N && j < N )
		{
		    Cell thisCell = matrix[ i++ ][ j++ ];
			if( thisCell != Cell.EMPTY )
				rightDiagnal.push( thisCell );
			else
				break;
		}
		
		Cell [] lineLeft = new Cell[ leftDiagnal.size() ];
		leftDiagnal.toArray( lineLeft );
		Cell [] lineRight = new Cell[ rightDiagnal.size() ];
		rightDiagnal.toArray( lineRight );
        
		checkLineLeft( lineLeft, k, result );
		checkLineLeft( lineRight, k, result );
		
	}
	
	private static void rightRotate( Cell [][] matrixAfter, Cell [][] matrixBefore )
	{
		int length = matrixBefore.length;
		for( int i = 0; i < length; i++ )
			for( int j = 0; j < length; j++ )
				matrixAfter[ i ][ j ] = matrixBefore[ length - j - 1 ][ i ];
	}
	
	private static void printMatrix( Cell [][] matrix )
	{
		int n = matrix.length;
		for( int i = 0; i < n; i++ )
		{
			for( int j = 0; j < n; j++ )
				System.out.print( matrix[ i ][ j ] + " " );
			System.out.println();
		}
	}
	
	// Member variables
	//private static final String INPUT_FILE_PATH = "D:/JavaIO/input.txt";
	private static final String INPUT_FILE_PATH = "D:/JavaIO/A-large-practice.in";
	private static final String OUTPUT_FILE_PATH = "D:/JavaIO/output.txt";
	
	public static void main( String args [] ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		String [] answers = new String[ numCases ];

		for( int i = 0; i < numCases; i++ )
		{
			String [] metadata = reader.readLine().split( " " );
		    int N = Integer.parseInt( metadata[ 0 ] );
		    int K = Integer.parseInt( metadata[ 1 ] );

		    Cell [][] matrix = new Cell[ N ][ N ]; 
		    for( int j = 0; j < N; j++ )
		    {
		    	String line = reader.readLine();
		    	for( int k = 0; k < line.length(); k++ )
		    	{
		    		char tmp = line.charAt( k );
		    		if( tmp == 'R' )
		    			matrix[ j ][ k ] = Cell.RED;
		    		else if( tmp == 'B' )
		    			matrix[ j ][ k ] = Cell.BLUE;
		    		else
		    			matrix[ j ][ k ] = Cell.EMPTY;
		    	}
		    }
		    
		    rightGravityMatrix( matrix );
		    Result r = new Result();
		    for( int j = 0; j < N; j++ )
		    {
		    	checkLineRight( matrix[ j ], K, r );
		    	if( r.Red && r.Blue )
		    		break;
		    }
		    
		    
		    Cell [][] matrixRightRotate = new Cell[ N ][ N ];
		    rightRotate( matrixRightRotate, matrix );
		    if( !r.Red || !r.Blue )
		    {
		    	for( int j = 0; j < N; j++ )
		    	{
		    		checkLineLeft( matrixRightRotate[ j ], K, r );
		    		if( r.Red && r.Blue )
		    			break;
		    	}
		    }
		    if( !r.Red || !r.Blue )
		    {
		    	for( int j = 0; j < N; j++ )
		    	{
		    		int k = 0;
		    		while( k < N && matrixRightRotate[ j ][ k ] != Cell.EMPTY )
		    		{
		    			checkDiagnal( matrixRightRotate, K, j, k++, r );
		    			if( r.Red && r.Blue )
		    				break;
		    			
		    		}
		    	}
		    }
		    if( r.Blue && !r.Red )
		    	answers[ i ] = "BLUE";
		    else if ( !r.Blue && r.Red )
		    	answers[ i ] = "RED";
		    else if ( !r.Blue && !r.Red )
		    	answers[ i ] = "NEITHER";
		    else
		    	answers[ i ] = "BOTH";
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
