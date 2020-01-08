import java.awt.event.KeyEvent;
import java.util.Scanner;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MiaMcDuffie extends ScrollingGame {

	//all of the imagaes used
	private final String USER_IMG = "images/person.png";    // specify user image file
	private final String AVOID_IMG = "images/bill.png";  // ADD others for Avoid/Get items
	private final String AVOID_IMG_2 = "images/horn.png";
	private final String GET_IMG = "images/money.png";
	private final String INTRO_IMG = "images/ink.png";
	private final String CLOSING_IMG = "images/ink.png";
	//private final String BACKGROUND = "images/background.png";
	private final String BACKGROUND = "images/background.jpg";
	private final String SECOND = "images/Second.png";
	private final String WIN = "images/win.jpg";
	private final String LOSE = "images/lose.png";
	//private boolean GAMEOVER = false;

public static final String[] IMG_INSTRUCTIONS = {
		"images/image1.png",
		"images/image2.png",
		"images/ins2.png"
		//null
	};

	//different states of the game
	private boolean paused = false;
	private boolean grid_on = false;
	private int pauseTime = 100;
	private boolean dead = false;
	private boolean won = false;
	private int difficulty = 0;

	/////////////////
	private int Gets;
	private int Avoids;
	private int userRow = 0;
	private int userCol = 0;
	////////////////
	public static final int MAX_UNITS = 3;
  public static int MAX_ROWS = 5;
	public static int MAX_COLS = 10;
	///////////////


	//constructors
	public MiaMcDuffie() {
		this(H_DIM, W_DIM, U_ROW);
	}

	public MiaMcDuffie(int hdim, int wdim, int urow) {
		MAX_ROWS = hdim;
		MAX_COLS = wdim;
		super.init(hdim, wdim, urow);
	}


	/******************** Methods **********************/

	// update game state to reflect adding in new cells in the right-most column
	public void populateRightEdge() {
		if (getScore() < 100) {
			first_phase();
		} else {
			if (difficulty - 1 == 0) {
				difficulty -= 1;
			}
			second_phase();
		}
	}

	public void first_phase() {
		int number = rand.nextInt(3);
		switch (number) {
			case 1:
						//place one
						int place_val = rand.nextInt(MAX_ROWS);
						int unit_val = rand.nextInt(MAX_UNITS);
						Location loc_place = new Location(place_val, MAX_COLS - 1);
						grid.setCellImage(loc_place, AVOID_IMG);
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
						grid.setCellImage(loc_place, AVOID_IMG);
						grid.setCellImage(loc_placetwo, GET_IMG);
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
						grid.setCellImage(loc_place, AVOID_IMG);
						grid.setCellImage(loc_placetwo, GET_IMG);
						grid.setCellImage(loc_placetwo, GET_IMG);
				default: //do nothing
						if (true) {
							break;
						}
				} //end of the switch
			}

	public void second_phase() {
		int number = rand.nextInt(4);
		switch (number) {
			case 1:
						//place one
						int place_val = rand.nextInt(MAX_ROWS);
						int unit_val = rand.nextInt(MAX_UNITS);
						Location loc_place = new Location(place_val, MAX_COLS - 1);
						grid.setCellImage(loc_place, AVOID_IMG);
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
						grid.setCellImage(loc_place, AVOID_IMG);
						grid.setCellImage(loc_placetwo, GET_IMG);
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
						grid.setCellImage(loc_place, AVOID_IMG);
						grid.setCellImage(loc_placetwo, GET_IMG);
						grid.setCellImage(loc_placetwo, AVOID_IMG_2);
						break;
				case 4:
					place_val = rand.nextInt(MAX_ROWS);
					place_valtwo = rand.nextInt(MAX_ROWS);
					place_valthree = rand.nextInt(MAX_ROWS);
					int place_valfour = rand.nextInt(MAX_ROWS);
					//
					unit_val = rand.nextInt();
					//
					loc_place = new Location(place_val, MAX_COLS - 1);
					loc_placetwo = new Location(place_valtwo, MAX_COLS - 1);
					loc_placethree = new Location(place_valthree, MAX_COLS - 1);
					Location loc_placefour = new Location(place_valfour, MAX_COLS - 1);
					//
					grid.setCellImage(loc_place, AVOID_IMG);
					grid.setCellImage(loc_placetwo, GET_IMG);
					grid.setCellImage(loc_placetwo, AVOID_IMG_2);
					grid.setCellImage(loc_placetwo, AVOID_IMG_2);
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
				if (grid.getCellImage(loc) == GET_IMG) {
					Gets += 1;
				} else if (grid.getCellImage(loc) == AVOID_IMG || grid.getCellImage(loc) == AVOID_IMG_2) {
					Avoids += 1;
				}
			}
		} catch (RuntimeException ex) {
			//checks if they go off screen
			if ((userRow < 0) || (userRow >= W_DIM) || (userRow <= W_DIM)) {
				System.out.println("You DIED since you went off screen");
				grid.setSplash(LOSE);
			}
		}
	}

	//---------------------------------------------------//




	// handles actions upon mouse click in game
	public void handleMouseClick() {

		Location loc = grid.checkLastLocationClicked();

		if (loc != null)
			System.out.println("You clicked on a square " + loc);
			//return true;

	}

	// handles actions upon key press in game
	public void handleKeyPress() {

		int key = grid.checkLastKeyPressed();

		//handels moving the image
		Location my_location = new Location(userRow, userCol);
		if (key == 38) {
			userRow -= 1;
			Location next_location = new Location(userRow, userCol);
			handleCollision(next_location);
			//update getCellImage setCellImage
			grid.setCellImage(next_location, USER_IMG);
			grid.setCellImage(my_location, null);

		} else if (key == 40) {
			userRow += 1;
			Location next_location = new Location(userRow, userCol);
			handleCollision(next_location);
			//updates
			grid.setCellImage(next_location, USER_IMG);
			grid.setCellImage(my_location, null);
		}
		// } else if (key == KeyEvent.VK_LEFT) {
		// 	userCol -= 1;
		// 	Location next_location = new Location(userRow, userCol);
		// 	handleCollision(next_location);
		// 	//updates
		// 	grid.setCellImage(next_location, USER_IMG);
		// 	grid.setCellImage(my_location, null);
		// } else if (key == KeyEvent.VK_RIGHT) {
		// 	userCol += 1;
		// 	Location next_location = new Location(userRow, userCol);
		// 	handleCollision(next_location);
		// 	//updates
		// 	grid.setCellImage(next_location, USER_IMG);
		// 	grid.setCellImage(my_location, null);
		// }

		//use Java constant names for key presses
		//http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN

		// Q for quit
		if (key == KeyEvent.VK_Q) {
			System.exit(0);
		} else if (key == KeyEvent.VK_S){
			//take a screenshot
			try {
			Robot robot = new Robot();
			String format = "jpg";
	    String fileName = "FullScreenshot." + format;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
			System.out.println("A full screenshot saved!");
		} catch (AWTException | IOException ex) {
        System.err.println(ex);
		//display the number of clicks
    } } else if (key == KeyEvent.VK_T)  {
			//speed up and slowdown the game
			boolean interval =  (timerClicks % FACTOR == 0);
			System.out.println("timerDelay " + timerDelay + " msElapsed reset " +
				msElapsed + " interval " + interval);
		//speeds up and slows down
		} else if (key == KeyEvent.VK_PERIOD) {
				//if try to change it less then difficulty than do nothing
				if (FACTOR + 1 < difficulty) {
				FACTOR += 1;
				}
		} else if (key == KeyEvent.VK_COMMA) {
			if (FACTOR > 1){
				FACTOR -= 1;
			}


		//pause the game
		} else if (key == KeyEvent.VK_P){
			paused = !paused;
			System.out.println("You paused the game!");
			while (paused) {
				key = grid.checkLastKeyPressed();
				if (key == KeyEvent.VK_P) {
					paused = !paused;
				}
				grid.sleep(1);
			}
			System.out.println("You resumed the game");
		} else if (key == KeyEvent.VK_D) {
			//System.out.println("hit");
			grid_on = !grid_on;
			if (!grid_on) {
				System.out.println("Grid on");
				grid.setLineColor("black");
			} else {
				System.out.println("Grid off");
				grid.setLineColor("null");
			}



		}

		/* To help you with step 9:
		   use the 'T' key to help you with implementing speed up/slow down/pause
    	   this prints out a debugging message */

	}

	// return the "score" of the game
	private int getScore() {
		int total = (Gets * 10) - (Avoids * 10);
		return total;
	}

	// update the title bar of the game window
	public void updateTitle() {
		grid.setTitle("Scrolling Game:  " + getScore());
	}

	// return true if the game is finished, false otherwise
	//      used by play() to terminate the main game loop
	public boolean isGameOver() {
		if (Avoids == 10) {
			System.out.println("You got hit ten times so you fail");
			return true;
		} else if (getScore() < -200) {
			System.out.println("Sorry, you failed!");

			return true;
		} else if (getScore() == 150){
			System.out.println("You Win!");
			return true;
		}
		return false;
	}


	// display the intro screen blank for now
	public void displayIntro(){
		//choose diffulculty
		System.out.println(grid.getLineColor());
		System.out.print("Welcome choose your difficulty from fastest to slowest (1-5): ");
		Scanner sc = new Scanner(System.in);
		int difficulty = sc.nextInt();
		FACTOR = difficulty;
		//set the background
		grid.setGameBackground(BACKGROUND);

		for (String instr : IMG_INSTRUCTIONS) {
			ClickThrough(instr);
		}
		grid.setSplash(null);
		grid.setGameBackground(BACKGROUND);
	}

	//goes through all of the instructions
	public void ClickThrough(String img) {
		//System.out.println("Hit");
		while (!grid.checkKeyPressed()) {
			grid.setSplash(img);
			grid.sleep(100);
		}
	}


	public void ClicktoClose() {
		while (grid.getKeyPress()) {
			grid.sleep(9999);
		}
		System.exit(0);
		//return true;
		//System.exit(0);
		// while (true) {
		// 	if (grid.getKeyPress() == true) {
		// 		System.exit(0);
		// 	}
		// }
	}

	public void displayOutcome() {
		if (getScore() < -200 || Avoids == 10) {
			grid.setSplash(LOSE);
			ClicktoClose();
		} else {
			grid.setSplash(WIN);
			ClicktoClose();
		}


		// if (ClicktoClose() == true) {
		// 	System.exit(0);
		// }

	}
}
