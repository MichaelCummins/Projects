import java.util.ArrayList;
import java.util.Scanner;

public class TextGame {
  // used for splitting the command and item from user input
  // used in takeItem, dropItem, and useItem
  static String itemName; 
  
  // constants for directions
  final static int NORTH = 0;
  final static int EAST = 1;
  final static int SOUTH = 2;
  final static int WEST = 3;
  /**
   * Instances of the Item constructor.
   * Each instance contains an item name, a description of the item, and the value of the item.
   */
  static Item[] items = {
     new Item ("flashlight", "Light it up!.", 10)
    ,new Item ("knife", " Perfect for stabbing.", 5)
    ,new Item ("needle", "Stich one, pearl two", 5)
    ,new Item ("key", "Shaped like a skeleton", 10)
    ,new Item ("fish", "It does nothing.", 15)
    ,new Item ("shoes", "Useful for looking nice.", 5)
    ,new Item ("brush", "Useful for getting knots out", 5)
    ,new Item ("thread", "Theres enough here to make a christmas sweater!", 15)
    ,new LimitedUseItem ("gun", "Perfect for shooting", 5, 3)
    ,new ComplexItem (null, null, 0, null, null) // empty elements
  };
  
  // instance of a ComplexItem
  static ComplexItem sweater = new ComplexItem ("sweater", "Lay it down somewhere.", 0, items[2], items[7]);
  
  
  /**
   * Instances of the Locale constructor.
   * Each instance contains a locale name and description of the locale.
   * Each locale is hard-coded through the constructor to contain an ArrayList and
   * to have the attribute visited set to false.
   */
  static Locale[] locations = {
     new Locale("Courtyard", "You can feel the darkness surrounding you.")
    ,new Locale("Castle Foyer", "It is quiet, however the carpet looks nice.")
    ,new SecureLocale("Dungeon", "Cells line the wall.", items[4]) // requires the fish
    ,new Locale("Library", "Shelves of books are looming around you.")
    ,new Locale("Dining room", "A long table with multiple place settings is in the center.")
    ,new Locale("Bedroom", "There is no one in here, but the bed is not made")
    ,new Locale("Indoor pool", "Care for a dip?")
    ,new SecureLocale("Basement", "The lowest point of the castle," 
  +"\nAlmost pitch black", items[3]) // requires the key
    ,new Locale("Stables", "You see multiple horses and moose in pens.")
  };  
  
  // navigation matrix
  static int[][] map = {
     {-1, 1, 3, -1}
    ,{-1, 2, 4, 0}
    ,{-1, -1, 5, 1}
    ,{0, 4, 6, -1}
    ,{1, 5, 7, 3}
    ,{2, -1, 8, 4}
    ,{3, 7, -1, -1}
    ,{4, 8, -1, 6}
    ,{5, -1, -1, 7}
  };
  
  public static void main(String[] args) {
    
    //initial declarations
    Player User = new Player(null, 4, null, null);  // instance of Player
    User.inventory = new ArrayList<Item>();      // new ArrayList for User inventory
    BreadcrumbTrail stack = new BreadcrumbTrail();  // instance of BreadcrumbTrail
    stack.dropCrumb(User.location);          // drop a crumb at the first location before moving
    items[9] = TextGame.sweater;          // replaces the null values
    
    /**
     * Each location's ArrayList is receiving at least one item except for the stables.
     */
    
    // add item(s) to a location
    locations[0].item.add(items[0]); // Courtyard contains the flashlight
    locations[1].item.add(items[3]); // Foyer contains the key
    locations[1].item.add(items[7]); // Foyer contains thread
    locations[2].item.add(items[2]); // Dungeon contains needle
    locations[3].item.add(items[8]); // Library contains gun
    locations[4].item.add(items[1]); // Dining Hall contains the knife
    locations[5].item.add(items[4]); // Bedroom contains key
    locations[6].item.add(items[5]); // Indoor Pool contains the shoes
    locations[7].item.add(items[6]); // Basement contains brush
    
    Scanner keyboard = new Scanner(System.in);
    String userInput; // used for entering commands
    // Beginning of game
    System.out.println("Welcome to Castle Zyrosa");
        System.out.println("-------------------------");
    System.out.print("I will be your guide" 
   +"\nJust call me Steward"
   +"\nWhat is your name visitor? ");
    User.name = keyboard.nextLine();
    
    System.out.print("Good evening " + User.name + " choose your gender: (male or female) ");
    User.gender = keyboard.nextLine().toLowerCase();
    
    /**
     * User choice. The female gender gives the player more moves.
     * The male starts with the key
     */
    
    while (!User.gender.equals("male") && !User.gender.equals("female")) {
      System.out.println("Choose your gender: (male or female) ");
      User.gender = keyboard.nextLine().toLowerCase();
    }
    System.out.println("");
    if (User.gender.equals("female")) {
  System.out.println("You have 5 additional moves!");
      User.actionCount -= 5; // additional moves
    } else if (User.gender.equals("male")) {
  System.out.println("You start with the key and can access the bedroom!");
      User.inventory.add(items[3]); // adds the key to the players inventory
      locations[1].item.remove(items[3]); // remove key from foyer
    }    
  System.out.println("");
  System.out.println(User.name + ". You are at the " 
            + locations[User.location].name 
            + ". " + locations[User.location].description);
  System.out.println("If you require help from Steward enter h");
  System.out.println("");
  displayHelp();
  System.out.println("");
  System.out.println("Your goal is to collect all of the times in the casle"
                    +"\nOr make the Sweater while in the stables to win the game");
    
    /**
     * This while loop repeatedly asks the user for a command.
     * If the length of the String input is greater than one, then the String is split.
     * The second element, which is [1], gets set to the variable itemName.
     * itemName gets passed onto the appropriate method, either takeItem, dropItem, or useItem.
     * If the length of the String input is one, then the appropriate method is called. 
     * For example, if the input is "i" then the showInventory() method is called.
     */
    // User input for commands, directions
    while (true) {
      System.out.print("\nEnter a command: ");
      userInput = keyboard.nextLine().toLowerCase().trim();
      
      // split the input string
      if (userInput.length() > 1) {
        
        // splits only the first occurrence of a space
        String[] splitString = userInput.split(" ", 2);
        
        // if the first command is for taking an item
        if (splitString[0].equals("t")) {
          // set the second element of the split to the item the user wants to take
          itemName = splitString[1];
          takeItem(User);
          
        } else if (splitString[0].equals("d")) {
          // set the second element of the split to the item the user wants to drop
          itemName = splitString[1];
          dropItem(User);
          
        } else if (splitString[0].equals("u")) {
          // set the second element of the split to the item the user wants to use
          itemName = splitString[1];
          useItem(User);
        } else if (splitString[0].equals("make")) {
          itemName = splitString[1];
          
          // search items array
          for (int i = 0; i < items.length; i++) {
            
            // if the name entered matches an item that exists
            if (itemName.equals(items[i].name)) {
              
              // if the item is a ComplexItem
              if (items[i] instanceof ComplexItem) {
                
                // cast the item, call canMake method
                ((ComplexItem) items[i]).canMake(User, items);
              }
            }
          }
        } else {
          System.out.println("Invalid command!");
        }
      }
      
      if (userInput.length() == 1) {
        if (userInput.equals("n")) {
          move(NORTH, User, stack);
          displayLoc(User);
        } else if (userInput.equals("e")) {
          move(EAST, User, stack);
          displayLoc(User);
        } else if (userInput.equals("s")) {
          move(SOUTH, User, stack);
          displayLoc(User);
        } else if (userInput.equals("w")) {
          move(WEST, User, stack);
          displayLoc(User);      
        } else if (userInput.equals("q")) {
          break;
        } else if (userInput.equals("h")) {
          displayHelp();
        } else if (userInput.equals("i")) {
          displayInventory(User);
        } else if (userInput.equals("m")) {
          displayMap(User);
        } else if (userInput.equals("b")) {
          if (Player.hasMoreActions(User)) {
            stack.goBack(User, stack, locations);
          }  
        } else if (userInput.equals("x")) {
          searchForItem(User);
        } 
        else {
          System.out.println("Invalid command!");
          continue;
        }
      }            
    } // end of loop for user input, commands, directions
    
    displayCredits(); 
    keyboard.close(); // cleans up resources
    
  } // end of main method








  /**
   * Searches a locale's ArrayList to see if there any items. If there is an item, the ArrayList gets looped through.
   * Then, the item's attribute of isDiscovered gets set to true.
   * @param User The user's location is taken in so that the user must be at the same locale in order to take the item.
   */
  
  // Searches a locale's ArrayList.
  static void searchForItem(Player User) {
    
    // if there is an item at a locale
    if (locations[User.location].item.size() > 0) {
      
      // search the locale's ArrayList
      for (int i = 0; i < locations[User.location].item.size(); i++) {
        
        // alert the user what they have found
        System.out.println("You have discovered the " + locations[User.location].item.get(i));
        
        // the item has now been discovered
        locations[User.location].item.get(i).isDiscovered = true;
      }
    // alert user there are no items at the locale
    } else {
      System.out.println("There are no items at this location.");
    }
  }
  
  /**
   * Checks if there is an item at the user's current locale.
   * If there is an item, the ArrayList gets looped.
   * If the global variable, itemName, is equal to the name of the item and the item has been discovered,
   * then the item gets added to the user's inventory. Next, the item gets removed from the locale's ArrayList.
   * @param User The user's location is required to make sure they take an item while at the correct locale.
   */
  
  // Adds an item to Player inventory if they have discovered the item.
  static void takeItem(Player User) {
    
    // tracks to see if an item has be taken
    Boolean itemTaken = false;
    
    // if there is an item at a location
    if (locations[User.location].item.size() > 0) {
      
      // search the ArrayList for the location
      for (int i = 0; i < locations[User.location].item.size(); i++) {
        
        // if the item name the user entered is at the locale
        if (itemName.equals(locations[User.location].item.get(i).name)) {
          
          // an item was found in the inventory
          itemTaken = true;
          
          // and if the item has been searched for
          if (locations[User.location].item.get(i).isDiscovered == true) {
            
            // check for actions remaining
            if (Player.hasMoreActions(User)){
              // alert the user what they picked up
              System.out.println("You have picked up the " + locations[User.location].item.get(i).name
                  + ".");
  
              
              // add item from locale ArrayList to inventory ArrayList
              User.inventory.add(locations[User.location].item.get(i));
              
              // get the item's value and add it to the Player's score
              User.score += locations[User.location].item.get(i).value;
              displayScore(User);
              
              // set the item's value to 0 to prevent double scoring
              locations[User.location].item.get(i).value = 0;
              
              // remove item locale array
              locations[User.location].item.remove(i);
              
              // if the player has all the items in their inventory, they win
              if (Player.hasAllItems(User)) {
                Player.winGame(User);
              }
            }
          }
        }
      }
      // an item was not found after looping through the location's item list
      if (itemTaken == false) {
        System.out.println("You cannot take that item.");
      }
      
    } else {
      System.out.println("Search the locale to take an item.");
    }
  }  
  
  /**
   * Checks if the user's inventory has any items in it. If there are items, then 
   * the inventory ArrayList gets looped through. If the global variable, itemName,
   * is equal to the name of the item in the inventory, the item gets added to the
   * locale the user is currently in. Then, the item is removed from the inventory.
   * @param User
   */
  
  // Drops an item from Player inventory
  static void dropItem(Player User) {
  
  // tracks to see if an item has been dropped
  Boolean itemDropped = false;
  
    // if the inventory is empty
    if (User.inventory.size() == 0) {
      System.out.println("You have no items to drop.");
      
    // else the inventory is not empty
    } else {
      
      // search the array
      for (int i = 0; i < User.inventory.size(); i++) {
        
        // if the item name the user entered is part of the inventory
        if (itemName.equals(User.inventory.get(i).name)) {
          
          // an item can be dropped
          itemDropped = true;
          
          // check for actions remaining
          if (Player.hasMoreActions(User)){
          
            // alert user what they dropped
            System.out.println("You have dropped the " + User.inventory.get(i).name + ".");
          
            // add the item to the locale ArrayList
            locations[User.location].item.add(User.inventory.get(i));
          
            // remove the item from the inventory ArrayList
            User.inventory.remove(User.inventory.get(i));
          }
        }
      }
      
      // an item was not found after looping through the inventory
      if (itemDropped == false) {
        System.out.println("You cannot drop that item.");
      }
    }
  }
  
  /**
   * Checks if the user's inventory has an item to use. If the global variable, itemName,
   * is equal to the name of an item in the inventory, then the next check is if the item
   * is a LimitedUseItem. If the item has limited uses, then the use() method is called,
   * which subtracts one use from the item. If the item has unlimited uses, then a message
   * is displayed to the console.
   * 
   * The variable itemUsed is implemented so that the user doesn't see "You cannot use that 
   * item" when the inventory is being searched.
   * @param User
   */
  
  static void useItem(Player User) {
    
    // tracks to see if an item has be used
    Boolean itemUsed = false;
    
    // if the user has no items to use
    if (User.inventory.size() == 0) {
      System.out.println("You have no items to use.");
      
    // else the user does have an item to use
    } else {
      
      // search the inventory array
      for (int i = 0; i < User.inventory.size(); i++) {
        
        // if the user typed in an item that exists in the inventory
        if (itemName.equals(User.inventory.get(i).name)) {
          
          // there is an item that can be used.
          itemUsed = true;
                    
          // check if the item is the map
          if (itemName.equals("map")) {
            displayMap(User);
          }
          
          // check if the item is a limited use item
          if (User.inventory.get(i) instanceof LimitedUseItem) {
            
            // cast the Item to a LimitedUseItem - allows access to the usesRemaining property
            LimitedUseItem currentItem = (LimitedUseItem) User.inventory.get(i);
            
            // Check uses remaining
            LimitedUseItem.use(currentItem);
            
          // for non-limited use items
          } else {
            System.out.println("You have used the " + itemName + ".");            
          }
        }
      }
    }
    
    // an item was not found after looping through the inventory
    if (itemUsed == false) {
      System.out.println("You cannot use that item.");
    }
    
  }
  
  /**
   * Takes in the user's current locale and the direction they wish to proceed in.
   * @param User
   * @param dir
   * @return
   */
  static int from (Player User, int dir) {
    return map[User.location][dir];
  }
  
  /**
   * Checks to see if there is a next locale. If there is, method checks if the next locale
   * is a SecureLocale. If it is, the canEnter method checks if the correct item is in the
   * user's inventory.
   * @param dir
   * @param User
   * @param stack
   */
  static void move (int dir, Player User, BreadcrumbTrail stack) {
    
    int nextLocation = from(User, dir);
    
    // if there is a next locale
     if (nextLocation != -1) {
       
       // check to see if the next locale is a secure locale
       if (locations[nextLocation] instanceof SecureLocale) {
         
         // if the user has the correct item
         // move the player to the secure locale / next location
         
         if (((SecureLocale) locations[nextLocation]).canEnter(User)) {
           User.location = nextLocation;
           stack.dropCrumb(User.location); // drops new crumb at new location    
          
        // the locale is locked, don't move the player
        } else {
          System.out.println("That area is locked. Add the correct item to your inventory "
              + "to enter.\n");
        }
      } else {
        // move the player to the next locale
        User.location = nextLocation;
        stack.dropCrumb(User.location); // drops new crumb at new location
      }
     } else {
       System.out.println("Try going another direction.\n");
     }
  }
  /**
   * If the user visits a new locale, the visited attribute
   * is changed to true and 5 points are added to the user's score.
   * @param User
   */
  static void playerScore(Player User) {
    // if the current locale has not been visited
    if (locations[User.location].visited == false) {
      // the locale has not been visited
      locations[User.location].visited = true;
      // update and show the updated score
      User.score += 5;
      System.out.println("Score: " + User.score);
    }
  }
  
  static void displayHelp() {
    String help =
       "Use the commands N, E, S, W to move around the island. \n"
      + "\"q\" quits the game.\n"
      + "\"t\" allows you to take an item. \n"
      + "\"d\" + item name allows you to drop an item. \n"
      + "\"u\" + item name allows you to use an item. \n"
      + "\"make\" + item name allows you to combine two items. \n"
      + "\"b\" allows you to backtrack to your previous location. \n"
  + "\"m\" will display the map.\n"
  + "\"i\" will display your inventory.\n"
  + "\"x\" will search your current area for items.\n";
      
      
    System.out.println(help);
  }
  
  /**
   * Prints out the items in the user's inventory
   * @param User
   */
  static void displayInventory(Player User) {
    ArrayList<Item> inv = User.inventory;
    // if the size of the list is zero
    if (inv.size() == 0) {
      System.out.println("You have no items in your inventory.");
    
    // else there are items in the inventory
    } else {
      System.out.println("Your items:"); 
      // loop through the list, print out each item
      for (int i = 0; i < inv.size(); i++) {
        System.out.println(inv.get(i));
      }
    }
  }
  
  /**
   * Displays the user's current location.
   * @param User
   */
  static void displayLoc(Player User) {
    System.out.println(locations[User.location]);
    playerScore(User); // calls the point tracking method
  }
  
  
  /**
   * Alerts the user how many points they have.
   * @param User
   */
  static void displayScore(Player User) {
    System.out.println("Score: " + User.score);
  }
  
  static void displayCredits() {
    // Credits 
  System.out.println("Thank you for playing Castle Zyrosa!");
  System.out.println("Developed by Michael Cummins");
  }
  
  /**
   * Displays the layout of the locations. The map must be in the 
   * user's inventory to use it.
   * @param User
   */
  static void displayMap(Player User) {
    System.out.println( "Courtyard  Foyer   Dungeon\n" +
            "Library  Dining hall  Bedroom\n" + 
            "Pool  Basement   Stables\n");
  }
}