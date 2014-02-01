/*===========================================
Human class (extends Player)
- Constructor
- hit method
- setup method
- uses System input to get information from user with getRow, getColumn, and getDirection for the placement of the ships.
- uses System input to get info from user with row1 and column1 about which coordinate to aim at.
- checks to make sure the person doesn't overlap or repeat hits at the same spot.
===========================================*/
public class Human extends Player {

    public Human() {
	super();
	board = new Board(true);
    }

    public void setup() {
	System.out.println("Placing ships on your board!");
	for ( int i = 0; i < ships.length; i++ ) {
	    System.out.println("You are now entering the information for your " + names[i] + ".");
	    boolean o = getDirection();
	    int r = getRow();
	    int c = getColumn();
	    boolean b = board.place( i, ships[i], o, r, c );
	    if (b) {
		System.out.println("This is your current board:");
		System.out.println(board);
	    }
	    else {
		System.out.println("\nSorry, you cannot place your ship there!  It doesn't fit, try again!\n");
		i--;
	    }
	}
    }//end setup()


    public int getRow() {
	boolean done = false;
	while (!done) {
	    System.out.println("Enter a number betweeen 0 and 9 for the row to place your ship:");
	    int i = getInt();
	    if ( i >= 0 && i <= 9 )
		return i;
	}
	return -1;
    }//end getRow()


    public int getColumn() {
	boolean done = false;
	while (!done) {
	    System.out.println("Enter a character from A to J for the column to place your ship:");
	    String s = getString().toUpperCase();
	    char c = s.charAt(0);
	    if ( c >= 'A' && c <= 'J' )
		return c - 'A';
	}
	return -1;
    }//end getColumn()
    
    
    public boolean getDirection() {
	boolean done = false;
	while (!done) {
	    System.out.println("Enter direction to place your ship:\n\t 1: Horizontal\n\t 2: Vertical");
	    int i = getInt();
	    if ( i == 1 )
		return true;
	    if ( i == 2 )
		return false;
	}
	return false;
    }//end getDirection()


    public int row1() {
	boolean done = false;
	while (!done) {
	    System.out.println("Enter a number between and including 0 and 9 for the row of where you want to attack:");
	    int i = getInt();
	    if ( i >= 0 && i <= 9 )
		return i;
	}
	return -1;
    }//end row1()


    public int column1() {
	boolean done = false;
	while (!done) {
	    System.out.println("Enter a character from A to J for the column of where you want to attack:");
	    String s = getString().toUpperCase();
	    char c = s.charAt(0);
	    if ( c >= 'A' && c <= 'J' )
		return c - 'A';
	}
	return -1;
    }//end column1()


    public int hit (Player other) {
	System.out.println("\nYour turn to shoot at the computer's board!\n");
	boolean done = false;
	while (!done) {
	    int r = row1();
	    int c = column1();
	    int i = other.board.mark( r, c );
	    if ( i == -1 ) {
		System.out.println ("\nSorry, you cannot aim at that spot!  You already tried to hit there!  Shoot again.\n");
	    }
	    else
		return i;
	}
	return -1;
    }//end hit()


    public static void main(String[] args) {
	Human a = new Human();
	a.setup();
    }//end main()

}//end class Human