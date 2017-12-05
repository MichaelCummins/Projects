public class ComplexItem extends Item {
  /**
    * A complex item is a combination of two items.
    * @param name
    * @param description
    * @param value
    */
  
  // item1 and item2 are the required items to make a ComplexItem
  Item item1, item2;

  public ComplexItem(String name, String description, int value, Item item1, Item item2) {
    super(name, description, value);
    this.item1 = item1;
    this.item2 = item2;
  }  
  /**
   * If the user has two items that go together, the user can make a new item.
   * The items must be present in the user's inventory.
   * @param locations 
   */
  
  Boolean canMake(Player User, Item[] items) {
    //if the user has the required items
    if (User.inventory.contains(this.item1) && User.inventory.contains(this.item2)) {  
      // if both items have uses remaining (if applicable)
      if (item1Pass(User) && (item2Pass(User))) {      
      // the ComplexItem is made
        System.out.println("You have made a " + TextGame.itemName + ".");
      // if the user makes the sweater at the stables, the player wins the game
        if (TextGame.itemName.equals("sweater") && User.location == 8) {
          Player.winGame(User);
        } 
          rewardPlayer(User); // subtracts actions from actionCount
        return true;
      }
    } else {
      System.out.println("You cannot make the " + TextGame.itemName + ".");
    }
    return false;
  }
  
  /**
    * Checks to see if the item is a limited use item. If it is, then the method returns true.
    * If the item is a limited use item, the use() method checks to see if there are uses remaining.
    * If there are uses remaining, then the method returns true.
    * @param User
    * @return
    */
  Boolean item1Pass (Player User) {
    
    // if the item is not a LimitedUseItem
    if (!(this.item1 instanceof LimitedUseItem)) {
      return true;
      // it is a LimitedUseItem
    } 
    else {
    // if there are uses remaining
    if (LimitedUseItem.use((LimitedUseItem) this.item1)) {
      return true;
      }
    }
    return false;
  }

  Boolean item2Pass (Player User) {
    
  // if the item is not a LimitedUseItem
    if (!(this.item2 instanceof LimitedUseItem)) {
      return true;
    // it is a LimitedUseItem
    } else {
    // if there are uses remaining
      if (LimitedUseItem.use((LimitedUseItem) this.item2)) {
    return true;
      }
    }
    return false;
  }
  
  void rewardPlayer(Player User) {
    // reward the player for making a ComplexItem
    if (User.actionCount >= 10) {
      User.actionCount -= 10;
    } else {
    User.actionCount = 0;
    }
  }  
}