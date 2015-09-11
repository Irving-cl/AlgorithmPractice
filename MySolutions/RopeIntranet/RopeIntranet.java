package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RopeIntranet {
	
	// Nest class
	private static class AVLTree<AnyType extends Comparable<? super AnyType>> {

		// Constructor
		public AVLTree() 
		{
			root = null;
		}
		
		// Insert
		public void insert(AnyType x) 
		{
			root = insert(x, root);
		}
		
		// Number of elements bigger than x
		public int elementsBiggerThan( AnyType x )
		{
			return nodesBiggerThan( x, root );
		}
		
		// Is empty?
		public boolean isEmpty() 
		{
			return root == null;
		}
		
		// Make empty
		public void makeEmpty() 
		{
			root = null;
		}
		
		// Print the tree
		public void printTree() 
		{
			if (isEmpty())
				System.out.println("Empty tree.");
			else
				printTree(root);
		}
		
		//===============================================
		// Internal methods
		//===============================================
		
		private int height(AvlNode<AnyType> t) 
		{
			return (t == null) ? -1 : t.height;
		}
		
		private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) 
		{
			AvlNode<AnyType> k1 = k2.left;
			k2.left = k1.right;
			k1.right = k2;
			k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
			k1.height = Math.max(height(k2), height(k1.left)) + 1;
			return k1;
		}
		
		private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) 
		{
			AvlNode<AnyType> k1 = k2.right;
			k2.right= k1.left;
			k1.left = k2;
			k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
			k1.height = Math.max(height(k2), height(k1.right)) + 1;
			return k1;
		}
		
		private AvlNode<AnyType> doubleRotateWithLeftChild( AvlNode<AnyType> k3 ) 
		{
			k3.left = rotateWithRightChild( k3.left );
			return rotateWithLeftChild( k3 );
		}
		
		private AvlNode<AnyType> doubleRotateWithRightChild(AvlNode<AnyType> k3) 
		{
			k3.right = rotateWithLeftChild(k3.right);
			return rotateWithRightChild(k3);
		}
		
		private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t ) 
		{
			if ( t == null )
				return new AvlNode<AnyType>( x );
			
			int compareResult = x.compareTo( t.element );
			if ( compareResult < 0 ) {
				t.left = insert( x, t.left );
				if ( height( t.left ) - height( t.right ) == 2 )
				    if ( x.compareTo( t.left.element ) < 0 )
					    t = rotateWithLeftChild( t );
				    else
				    	t = doubleRotateWithLeftChild( t );
			} else if ( compareResult > 0 ) {
				t.right = insert( x, t.right );
				if ( height( t.right ) - height( t.left ) == 2 )
					if ( x.compareTo( t.right.element ) > 0 )
						t = rotateWithRightChild( t );
					else
						t = doubleRotateWithRightChild( t );
			}
			else
				;
			t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
			return t;
		}
		
		private int nodesBiggerThan( AnyType x, AvlNode<AnyType> t )
		{
			if( t == null )
				return 0;
			
			int num = 0;
			for( ; ; )
			{
				int compareResult = x.compareTo( t.element );
				if( compareResult < 0 )
				{
				    num +=  ( 1 + treeSize( t.right ) );
				    if( t.left == null || x.compareTo( findMax( t.left ).element ) >= 0 )
				    	break;
				    t = t.left;
				}
				else if( compareResult >= 0 )
				{
					if( t.right == null )  
						break;
					
					else if( x.compareTo( findMin( t.right ).element ) < 0 )
					{
						num += treeSize( t.right );
						break;
					}
					t = t.right;
				}
			}
			
			return num;
		}
		
		private AvlNode<AnyType> findMax( AvlNode<AnyType> t ) 
		{
			if ( t != null )		
			    while ( t.right != null )
				    t = t.right;
			return t;
		}
		
		private AvlNode<AnyType> findMin( AvlNode<AnyType> t ) 
		{
			if ( t != null )		
			    while ( t.left != null )
				    t = t.left;
			return t;
		}
		
		private int treeSize( AvlNode<AnyType> t )
		{
			if( t == null )
				return 0;
			else if( t.left == null && t.right == null )
				return 1;
			else 
				return 1 + treeSize( t.left ) + treeSize( t.right );
		}
		
		private void printTree( AvlNode<AnyType> t ) 
		{
			if ( t != null ) 
			{
				printTree( t.left );
				System.out.println( t.element );
				printTree( t.right );
			}
		}
		
		// Nested class
		private static class AvlNode<AnyType> 
		{
			
			AvlNode(AnyType theElement) 
			{
				this(theElement, null, null);
			}
			
			AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) 
			{
				element = theElement;
				left = lt;
				right = rt;
				height = 0;
			}
			
			AnyType element;              // The data in the node
			AvlNode<AnyType> left;        // Left child
			AvlNode<AnyType> right;       // Right child
			int height;                   // Height
		}
		
		// Root node
		AvlNode<AnyType> root;
		
	}

	private static class Wire implements Comparable<Wire>
	{
		Wire( int a, int b )
		{
			portA = a;
			portB = b;
		}
		
		int portA;
		int portB;
		
		public int compareTo( Wire w ) {
			return portA - w.portA;
		}
		
	}
	
	// Member variables
	//private static final String INPUT_FILE_PATH = "D:/JavaIO/A-large-practice.in";
	private static final String INPUT_FILE_PATH = "D:/JavaIO/input.txt";
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
			int numWires = Integer.parseInt( reader.readLine() );
			List<Wire> wireList = new ArrayList<Wire>();
			AVLTree<Integer> existedRightWindows = new AVLTree<Integer>();
			for( int j = 0; j < numWires; j++ )
			{
				String [] wireStr = reader.readLine().split( " " );
				wireList.add( new Wire( Integer.parseInt( wireStr[ 0 ] ),
						Integer.parseInt( wireStr[ 1 ] ) ) );
			}
			wireList.sort( null );
			
			int intersections = 0;
			for( int j = 0; j < numWires; j++ )
			{
				intersections += existedRightWindows.elementsBiggerThan( wireList.get( j ).portB );
				existedRightWindows.insert( wireList.get( j ).portB );
			}
			answers[ i ] = intersections;
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
