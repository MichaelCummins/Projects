import java.util.ArrayList;
public class Locale {
  
  /**
    * The locale constructor has attributes for a name, description, an item ArrayList, itemTaken, and visited.
    */
  
  String name, description;
  ArrayList<Item> item;
  Boolean itemTaken, visited;  
  public Locale (String name, String description) {
    this.name = name;
    this.description = description;
    this.item = new ArrayList<Item>();
    this.itemTaken = false;
    this.visited = false;
  }
  
  @Override
  public String toString() {
  return "Location: the " + this.name + ". " + this.description;
  }
}