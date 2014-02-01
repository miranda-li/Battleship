/*==============================================
AI Class
- similar to Human, except with random methods of marking and placing.
==============================================*/

import java.io.*;
import java.util.*;

public class AI extends Player {

    public AI() {
	super();
	board = new Board(false);
    }//end constructor


    public void setup() {
	for ( int i = 0; i < ships.length; i++ ) {
	    int row = (int)(Math.random() * 10 );
	    int column = (int)(Math.random() * 10 );
	    boolean direction;
	    if ( (int)(Math.random() * 2) == 0 )
		direction = true;
	    else
		direction = false;
	    boolean test = board.place( i, ships[i], direction, row, column );
	    if (!test)
		i--;
	}
    }//end setup()


    public int hit(Player other) {
	boolean done = false;
	while (!done) {	
	    int row = (int)(Math.random() * 10 );
	    int column = (int)(Math.random() * 10);
	    int i = other.board.mark( row, column );
	    if ( i != -1 )
		return i;
	}
	return -1;
    }//end hit()

}//end class AI