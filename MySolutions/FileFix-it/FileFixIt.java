package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileFixIt {

	// Nested class
	private static class BTree
	{
		public BTree()
		{
			root = new BNode( "root" );
		}
		
		public void add( String dirName )
		{
			String [] path = dirName.substring( 1 ).split( "/" );
			BNode currentDir = root;
			for( int i = 0; i < path.length; i++ )
			{		
				BNode nextDir = searchInDir( currentDir, path[ i ] );
				if( nextDir == null )
				{
					BNode lastChild = lastChild( currentDir );
					if( lastChild == null )
					{
						currentDir.firstChild = new BNode( path[ i ] );
						nextDir = currentDir.firstChild;
					}
					else
					{
						lastChild.next = new BNode( path[ i ] );
						nextDir = lastChild.next;
					}
						
				}
				currentDir = nextDir;
			}
		}
		
		public int size()
		{
			return sizeOf( root ) - 1;
		}
		
		// Member variables
		BNode root;
		
		// Nested class
		private static class BNode
		{
			BNode( String directoryName )
			{
				dirName = directoryName;
				firstChild = null;
				next = null;
			}
			
			String dirName;
			BNode firstChild;
			BNode next;
		}
		
		// Internal method
		private BNode searchInDir( BNode parent, String dirName )
		{
			BNode child = parent.firstChild;
			while( child != null )
			{
				if( child.dirName.equals( dirName )  )
					return child;
				child = child.next;
			}
			return null;
		}
		
		private BNode lastChild( BNode parent )
		{
			BNode firstChild = parent.firstChild;
			if( firstChild == null )
				return null;
			while( firstChild.next != null )
				firstChild = firstChild.next;
			return firstChild;
		}
		
		private int sizeOf( BNode root )
		{
			if( root == null )
				return 0;
			else if( root.firstChild == null )
				return 1;
			else
			{
				BNode child = root.firstChild;
				int size = 1;
				while( child != null)
				{
					size += sizeOf( child );
					child = child.next;
				}
				return size;
			}
		}
		
	}
	
	private static final String INPUT_FILE_PATH = "D:/JavaIO/A-large-practice.in";
	//private static final String INPUT_FILE_PATH = "D:/JavaIO/input.txt";
	private static final String OUTPUT_FILE_PATH = "D:/JavaIO/output.txt";
	
	public static void main( String [] args ) throws NumberFormatException, IOException
	{
		File file = new File( INPUT_FILE_PATH );
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		
		// Read in number of cases and new messages
		int numCases = Integer.parseInt( reader.readLine() );
		int [] answers = new int[ numCases ];
		
		for( int i = 0; i < numCases; i++ )
		{
			// Read in one case
			String [] metaData = reader.readLine().split( " " );
			int numExistedDirs = Integer.parseInt( metaData[ 0 ] );
			int numDirsToMake = Integer.parseInt( metaData[ 1 ] );
			BTree fileSystem = new BTree();
			for( int j = 0; j < numExistedDirs; j++ )
				fileSystem.add( reader.readLine() );
			int numRealExistedDirs = fileSystem.size();
			for( int j = 0; j < numDirsToMake; j++ )
				fileSystem.add( reader.readLine() );
			answers[ i ] = fileSystem.size() - numRealExistedDirs;
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
