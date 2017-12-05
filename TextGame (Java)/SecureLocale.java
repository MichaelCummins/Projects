public class SecureLocale extends Locale {
  // a requiredItem must be in the inventory to enter the secure locale.
  Item requiredItem;
  public SecureLocale (String name, String description, Item requiredItem) {
    super(name, description);
    this.requiredItem = requiredItem;
  }
  /**
    * @param User
    * @return Returns true if the user has the required item in the inventory
    */
  
  Boolean canEnter (Player User) {
    if (User.inventory.contains(this.requiredItem)) {
      return true;
    }
      return false;
  }
}