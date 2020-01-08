import java.awt.event.KeyEvent;

public class BaseGame extends ScrollingGame {

	//all of the imagaes used
	private final String USER_IMG = "user.gif";    // specify user image file
	private final String AVOID_IMG = "avoid.gif";  // ADD others for Avoid/Get items
	private final String GET_IMG = "get.gif";
	private final String INTRO_IMG = "ink.png";
	private final String CLOSING_IMG = "ink.png";


	//different states of the game
	private boolean paused = false;
	private boolean dead = false;
	private boolean won = false;

	/////////////////
	private int Gets;
	private int Avoids;
	private int userRow = 0;
	////////////////
	public static final int MAX_UNITS = 3;
  public static final int MAX_ROWS = 5;
	public static final int MAX_COLS = 10;

	//constructors
	public BaseGame() {
		this(H_DIM, W_DIM, U_ROW);
	}

	public BaseGame(int hdim, int wdim, int urow) {
		super.init(hdim, wdim, urow);
	}

	/******************** Methods **********************/

	// update game state to reflect adding in new cells in the right-most column
	public void populateRightEdge() {
		int number = rand.nextInt(3);
		switch (number) {
			case 1:
						//place one
						int place_val = rand.nextInt(MAX_ROWS);
						int unit_val = rand.nextInt(MAX_UNITS);
						Location loc_place = new Location(place_val, MAX_COLS - 1);
						grid.setCellImage(loc_place, "avoid.gif");
						break;
			case 2:
						//place two
						place_val = rand.nextInt(MAX_ROWS);
						int place_valtwo = rand.nextInt(MAX_ROWS);
						//
						unit_val = rand.nextInt(MAX_UNITS);

						loc_place = new Location(place_val, MAX_COLS - 1);
						Location loc_placetwo = new Location(place_valtwo, MAX_COLS - 1);
						//
						grid.setCellImage(loc_place, "avoid.gif");
						grid.setCellImage(loc_placetwo, "get.gif");
						break;
			case 3:
						place_val = rand.nextInt(MAX_ROWS);
						place_valtwo = rand.nextInt(MAX_ROWS);
						int place_valthree = rand.nextInt(MAX_ROWS);
						//
						unit_val = rand.nextInt();
						//
						loc_place = new Location(place_val, MAX_COLS - 1);
						loc_placetwo = new Location(place_valtwo, MAX_COLS - 1);
						Location loc_placethree = new Location(place_valthree, MAX_COLS - 1);
						//
						grid.setCellImage(loc_place, "avoid.gif");
						grid.setCellImage(loc_placetwo, "get.gif");
						grid.setCellImage(loc_placetwo, "get.gif");
				default: //do nothing
						if (true) {
							break;
						}
				} //end of the switch
	}

	// updates the game state to reflect scrolling left by one column
	public void scrollLeft() {
				Location loc_user = new Location(userRow, 0);
				for (int i = 0; i < MAX_ROWS; i++) {
					for (int j = 0; j < MAX_COLS; j++) {
						Location loc_current = new Location(i, j);
						if (!loc_current.equals(loc_user)) {
							int temp = j - 1;
							Location loc_set = new Location(i, temp);
							if (temp < 0) {
								grid.setCellImage(loc_current, null); //error
							} else if (!loc_current.equals(new Location(userRow, 1))) {
								grid.setCellImage(loc_set, grid.getCellImage(loc_current));
							}
							if (j == MAX_COLS - 1) {
								grid.setCellImage(loc_current, null);
							}
						}
						if (loc_current.equals(new Location(userRow, 1))) {
								handleCollision(loc_current);
						}
					}

		}
	}

	// returns the user image
	public String getUserImg() {
		return USER_IMG;
	}

	/* handleCollision()
	 * handle a collision between the user and an object in the game
	 */
	public void handleCollision(Location loc) {
		try {
			if (grid.getCellImage(loc) == null) {
				//do nothing
			} else {
				if (grid.getCellImage(loc) == "get.gif") {
					Gets += 1;
				} else if (grid.getCellImage(loc) == "avoid.gif") {
					Avoids += 1;
				}
			}
		} catch (RuntimeException ex) {
			//checks if they go off screen
			if ((userRow < 0) || (userRow >= W_DIM)) {
				System.out.println("You DIED since you went off screen");
			}
		}
	}

	//---------------------------------------------------//

	// handles actions upon mouse click in game
	public void handleMouseClick() {

		Location loc = grid.checkLastLocationClicked();

		if (loc != null)
			System.out.println("You clicked on a square " + loc);

	}

	// handles actions upon key press in game
	public void handleKeyPress() {

		int key = grid.checkLastKeyPressed();

		Location my_location = new Location(userRow, 0);
		if (key == 38) {
			userRow -= 1;
			Location next_location = new Location(userRow, 0);
			handleCollision(next_location);
			//update getCellImage setCellImage
			grid.setCellImage(next_location, USER_IMG);
			grid.setCellImage(my_location, null);
		} else if (key == 40) {
			userRow += 1;
			Location next_location = new Location(userRow, 0);
			handleCollision(next_location);
			//updates
			grid.setCellImage(next_location, USER_IMG);
			grid.setCellImage(my_location, null);
		}

		//use Java constant names for key presses
		//http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN

		// Q for quit
		if (key == KeyEvent.VK_Q)
			System.exit(0);

		else if (key == KeyEvent.VK_S)
			System.out.println("could save the screen: add the call");

		/* To help you with step 9:
		   use the 'T' key to help you with implementing speed up/slow down/pause
    	   this prints out a debugging message */
		else if (key == KeyEvent.VK_T)  {
			boolean interval =  (timerClicks % FACTOR == 0);
			System.out.println("timerDelay " + timerDelay + " msElapsed reset " +
				msElapsed + " interval " + interval);
		}
	}

	// return the "score" of the game
	private int getScore() {
		int total = (Gets * 10) - (Avoids * 10);
		//int total = (int) Math.round((Gets - (Avoids * 3)) * (msElapsed / 300));
		return total;
	}

	// update the title bar of the game window
	public void updateTitle() {
		grid.setTitle("Scrolling Game:  " + getScore());
	}

	// return true if the game is finished, false otherwise
	//      used by play() to terminate the main game loop
	public boolean isGameOver() {
		if (getScore() < -200) {
			System.out.println("Soory, you failed!");
			return true;
		} else if (getScore() == 100){
			System.out.println("You Win!");
			return true;
		}
		return false;
	}

	// display the intro screen blank for now
	public void displayIntro(){
	
	}

	// display the game over screen, blank for now
	public void displayOutcome() {

	}
}
