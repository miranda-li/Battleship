/*==============================================
PLAYER CLASS

    You should have an abstract class called Player and then 2 subclasses, one for a human player and one for a computer player.
    The Player class should be able to keep track of a players ships and the ships' states.
    It should have methods to set up ships on a board and make a mark on an opponents board (these methods will be different for the human and computer classes)
    The Player class should also provide a method to determine if the player has lost the game
    You can use any instance variables and methods you would need.
=================================================

NOTE: Sorry this is late.  I was absent on Monday so I didn't know it was due then, and then the submission site stopped working, so I'm submitting it now.

DESIGN PLAN
Player Class (abstract)

Instance Variables
int[] ships: array size 5; the index represents the ship, the value represents the number of "spots" left for the ship.
Board: each player has a board.

Methods
- getString and getInt for System input
- checkLost: checks to see if somebody has lost the game, returns boolean.
- abstract methods hit and setup.
==============================================*/

import java.io.*;
import java.util.*;

public abstract class Player {

    public int[] ships= {Board.DESTROYERLENGTH, Board.CRUISERLENGTH, Board.SUBMARINELENGTH, Board.BATTLESHIPLENGTH, Board.CARRIERLENGTH};
   
 public String[] names = {"Destroyer", "Cruiser", "Submarine", "Battleship", "Aircraft Carrier"};
    public Board board;
    
    public String getString() {
	InputStreamReader in;
	BufferedReader bin;
	in = new InputStreamReader(System.in);
	bin = new BufferedReader(in);
	String s = "";
	try {
	    s = bin.readLine();
	} catch (IOException e) {
	    System.out.println("IO exception");
	}
	return s;
    }//end getString
    
    public int getInt() {
	return Integer.parseInt( getString() );
    }//end getInt
    
    
    public Player() {
    }//end constructor
    
    public abstract int hit( Player other);
    public abstract void setup();
    
    public boolean checkLost() {
	int sum = 0;
	for ( int i = 0; i < ships.length; i++ )
	    sum = sum + ships[i];
	if (sum <= 0) 
	    return true;
	return false;
    }//end checkLost()

}//end class Player