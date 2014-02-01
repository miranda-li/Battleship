/*===========================================
Board Class
-Represent a 10x10 grid for battleship and be able to "contain" ships.
-The board should be able to differentiate between empty spaces, spaces that are registered as "misses" spaces that are registered as "hits" and spaces with ships in them.
-Display a board with coordinates clearly marked
-Display a board that shows all the ships and another that only shows hits and misses (hiding the actual ship positions).
-Have method(s) to allow for ship placement, ensuring that ships are placed according to the rules of the game
-Have Method(s) that will record a mark in the game, and in some way return whether the mark was a hit or not (this also may somehow return the type of ship hit in the event of a successful mark.
-You may include as many methods and instance variables as you see fit.
==============================================
Design Plan

Note: I resubmitted this 1/11/12 with the instance variables changed from private to public.

Instance variables
- int[][] grid; 10 x 10 2-D array that represents the board.  It will be marked with 0 through 4 for Destroyer through aircraft carrier, 10 for a space, 11 for a hit, and 12 for a miss.
- constants for the size and type of the ships.
- boolean showShips to determine if ships are shown in toString.

Methods

Constructor(boolean show); instantiates grid, and sets all of the ints to 10.  It sets showShips to show.

void toString
   Creates an 11 x 11 2-D char array, tempGrid, and sets the first row the numbers 1 - 10, and the first column the letters A - J.  tempGrid[0][0] will just be empty.
   Sets the rest of tempGrid based on grid, using different characters.
   Creates a String s and displays it just like the toString of the WordSearch class.
   If showShips is false then the ships won't be shown.

boolean place(int type, int size, boolean direction, int row, int column)
   If direction is true, then the ship will be placed horizontally; if false, then it will be placed vertically.
   Row represents the index of the row.
   Column represents the index of the column.
   Place will check to see if the given size fits based on the direction, row, and column.  If it doesn't, or hits anything other than empty spaces, the method will return false.
   If the ship can successfully fit, then the method will change the integers and return true.

int mark(int row, int column)
   Returns an integer based on the type of thing hit (ship, miss, or out of bounds).
   =================================================*/

import java.io.*;
import java.util.*;

public class Board {

    public static final int DESTROYER = 0;
    public static final int CRUISER = 1;
    public static final int SUBMARINE = 2;
    public static final int BATTLESHIP = 3;
    public static final int CARRIER = 4;
    public static final int EMPTY = 10;
    public static final int HIT = 11;
    public static final int MISS = 12;
    public static final int DESTROYERLENGTH = 2;
    public static final int CRUISERLENGTH = 3;
    public static final int SUBMARINELENGTH = 3;
    public static final int BATTLESHIPLENGTH = 4;
    public static final int CARRIERLENGTH = 5;

    public int[][] grid;
    public boolean showShips;


    public Board(boolean show) {
	showShips = show;
	grid = new int[10][10];
	for ( int i = 0; i < grid.length; i++ )
	    for ( int e = 0; e < grid[0].length; e++ )
		grid[i][e] = 10;
    }//end constructor


    public String toString() {
	char[][] tempGrid = new char[11][11];
	char letters = 'A';
	char numbers = '0';
	for ( int i = 1; i < tempGrid.length; i++ ) {
	    tempGrid[i][0] = numbers;
	    tempGrid[0][i] = letters;
	    letters++;
	    numbers++;
	}
	tempGrid[0][0] = ' ';
	for ( int i = 1; i < tempGrid.length; i++ )
	    for ( int e = 1; e < tempGrid[0].length; e++ ) {
		if ( grid[i - 1][e - 1] == MISS )
		    tempGrid[i][e] = 'M';
		else if ( grid[i - 1][e - 1] == HIT )
		    tempGrid[i][e] = 'H';
		else 
		    tempGrid[i][e] = '~';
	    }
	if (showShips)
	    for ( int i = 1; i < tempGrid.length; i++ )
		for ( int e = 1; e < tempGrid[0].length; e++ )
		    if ( grid[i - 1][e - 1] == DESTROYER || grid[i - 1][e - 1] == CRUISER ||
			 grid[i - 1][e - 1] == SUBMARINE ||  grid[i - 1][e - 1] == BATTLESHIP ||
			 grid[i - 1][e - 1] == CARRIER )
			tempGrid[i][e] = 'S';

	String s1 = "\n";
	for (int r = 0; r < tempGrid.length; r++) {
	    for (int c = 0; c < tempGrid[r].length; c++) {
		s1+= tempGrid[r][c] + " ";
	    }
	    s1+= "\n";
	}
	return s1;
    }//end toString()
    
    
    public boolean place(int type, int size, boolean direction, int row, int column) {
	if ( row < 0 || column < 0)
	    return false;
	if (direction) {
	    if ( column + size > grid[0].length )
		return false;
	    for ( int c = column; c < column + size; c++ ) 
		if ( grid[row][c] != EMPTY )
		    return false;
	    for ( int c = column; c < column + size; c++ )
		grid[row][c] = type;
	}
	
	else {
	    if ( row + size > grid.length )
		return false;
	    for ( int r = row; r < row + size; r++ )
		if ( grid[r][column] != EMPTY )
		    return false;
	    for (int r = row; r < row + size; r++ )
		grid[r][column] = type;
	}
	return true;
    }//end place()
    
    
    public int mark(int r, int c) {
	if ( r < 0 || c < 0 || r > grid.length || c > grid[0].length )
	    return -1;
	else if ( grid[r][c] == EMPTY ) {
	    grid[r][c] = MISS;
	    return MISS;
	}
	else if ( grid[r][c] == DESTROYER ) {
	    grid[r][c] = HIT;
	    return DESTROYER;
	}
	else if ( grid[r][c] == CRUISER ) {
	    grid[r][c] = HIT;
	    return CRUISER;
	}
	else if ( grid[r][c] == SUBMARINE ) {
	    grid[r][c] = HIT;
	    return SUBMARINE;
	}
	else if ( grid[r][c] == BATTLESHIP ) {
	    grid[r][c] = HIT;
	    return BATTLESHIP;
	}
	else if ( grid[r][c] == CARRIER ) {
	    grid[r][c] = HIT;
	    return CARRIER;
	}
	return -1;
    }//end mark()


    public static void main( String[] args ) {
	Board a = new Board(true);
	Board b = new Board(false);
	a.place (CARRIER, CARRIERLENGTH, true, 2, 3);
	a.place (BATTLESHIP, BATTLESHIPLENGTH, false, 1, 5);
	a.place (BATTLESHIP, BATTLESHIPLENGTH, false, 3, 4);
	b.place (DESTROYER, DESTROYERLENGTH, true, 9, 8);
	b.place (CRUISER, CRUISERLENGTH, true, -1, 5);
	b.place (SUBMARINE, SUBMARINELENGTH, false, 6, 7);
	a.mark(0, 9);
	a.mark(4, 4);
	b.mark(0, 2);
	b.mark(6, 7);
	System.out.println(a);
	System.out.println(b);
    }//end main()

}//end class Board

