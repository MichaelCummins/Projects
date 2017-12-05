public class LimitedUseItem extends Item {
  /**
    * A limited use item can only be used if usesRemaining is greater than zero. LimitedUseItem
    * is the child class and the parent class is Item. An instance of the LimitedUseItem has a name,
    * description, and value just like an instance of Item. 
    */
  
  // An integer that tracks how many times the user can use an item.
  int usesRemaining;

  // Contains the Item attributes with the new integer usesRemaining
  public LimitedUseItem(String name, String description, int value, int usesRemaining) {
    super(name, description, value);
    this.usesRemaining = usesRemaining;
  }
  
  /**
    * Activates a LimitedUseItem and reduces the count by 1. Returns true
    * if the item has more uses, or false if the item cannot be used
    * anymore.
    * @return
    */
  
  public static boolean use(LimitedUseItem x) {
    if (x.usesRemaining > 0) {
      x.usesRemaining--;
      System.out.println("You have used the " + x.name + ". Uses remaining: "
               + x.usesRemaining);
      return true;
    } else {
        System.out.println("You cannot use this item anymore.");
      return false;
    }
  }
}