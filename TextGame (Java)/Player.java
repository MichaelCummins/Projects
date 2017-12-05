import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  /**
    * The Player constructor has attributes for a name, starting location, inventory, score, and gender.
    */
	
  String name, gender;
  int score, location;
  int actionCount;
  ArrayList<Item> inventory;
	
  /**
    * @param name a String for the player's name.
    * @param location the user's current locations.
    * @param inventory an ArrayList that contains the user's items
    * @param score the score gets updated by adding an item, visiting a new locale. Starts at 5.
    * @param gender allows for player customization
    */
	
  public Player (String name, int location, ArrayList<Item> inventory, String gender) {
    this.name = name;
    this.location = 0; //sets player Location to the courtyard
    this.inventory = inventory;
    this.score = 5; 
    this.actionCount = 0;
    this.gender = gender;
  }
	
	/**
	 * 
	 * @return returns true if the user has actions available to use. It returns false
	 * and ends the game if actionCount is no longer less than 20. 
	 */
  static Boolean hasMoreActions(Player User) {
    if (User.actionCount < 20){
      User.actionCount++;
      return true;
    }
    endGame(User);
      return false;
    }
  static Boolean hasAllItems (Player User) {
    if (User.inventory.size() == 9) {
      return true;
    }
      return false;		
	}
  static void winGame(Player User) {
    System.out.println( User.name + " You won!!!!!!!!");
    TextGame.displayCredits();
    System.exit(0);
  }
  /**
    * Game ended and program stopped
    */
  static void endGame(Player User) {
    System.out.println("Sorry, " + User.name + ". You have ran out of actions. Game over.\n");
    TextGame.displayCredits();
    System.exit(0);
  }
  @Override
  public String toString() {
  return this.name + " " + this.location + " " + this.score + this.inventory;
  }
}