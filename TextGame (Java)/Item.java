public class Item {
  /**
    * Each instance of an item will have a name, description, and value. Each item is automatically set
    * to be not discovered.
    */
  String name, description;
  Boolean isDiscovered;
  int value;

  public Item (String name, String description, int value) {
    this.name = name;
    this.description = description;
    this.isDiscovered = false;
    this.value = value;
  }

  @Override
  public String toString() {
    return name + " - " + description;
  }  
}