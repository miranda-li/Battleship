/*=================================================
GameDriver class
Puts all the other classes together!

DESIGN PLAN
No instance variables
Constructor method runs everything:
- First, player puts in ships.
- Player attacks, and checks for loss.
- Computer attacks, and checks for loss.
- The ints in the ships array in the Player class are reduced as the ships are hit.

main method runs constructor
=================================================*/

import java.io.*;
import java.util.*;

public class GameDriver {

    public GameDriver() {

    System.out.println("\nWELCOME TO BATTLESHIP!  You play as 'Player 1' against the computer.  If you lose, feel free to open up the AI.java file and make the computer stupid, so you can at least feel a bit better about yourself.\n\n HAVE FUN!\n");
    Human a = new Human();
    a.setup();
    AI b = new AI();
    int n = 0;
    int i;

    while (n < 200) {
	i = a.hit(b);
	if (i == Board.MISS)
	    System.out.println ("YOU MISSED!");
	if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 ) {
	    System.out.println("You hit a ship!");
	    b.ships[i]--;
	    if( b.ships[i] == 0 )
		System.out.println("You sunk the AI's "+ a.names[i] + "!!");
	}

	n++;
	if ( !a.checkLost() ) {
	    i = b.hit(a);
	    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 ) {
		System.out.println("One of your ships got hit!");
		a.ships[i]--;
		if( a.ships[i] == 0 )
		    System.out.println("The AI sunk your "+ a.names[i] + "!!");
	    }
	    
	    System.out.println("\nThese are the states of the two boards after exchanging attacks:\nYOU:\n" + a.board + "\nAI:\n" + b.board);
	    n++;
	}
	else {
	    System.out.println("You've lost against the AI!  TRY AGAIN!");
	    return;
	}

	if ( b.checkLost() ) {
	    System.out.println("CONGRATS, you've beaten the AI!  PLAY AGAIN!");
	    return;
	}
    }
 }//end constructor
    
    public static void main(String[] args) {
	GameDriver a = new GameDriver();
    }//end main()
    
}//end class GameDriver