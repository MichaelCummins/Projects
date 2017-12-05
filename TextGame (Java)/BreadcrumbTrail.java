public class BreadcrumbTrail {
  
  /**
   * A breadcrumb is dropped at the starting location and whenever the user moves to a location, a breadcrumb is dropped.
   * To backtrack to the previous location, the hasMoreCrumbs method is called to make sure that the user isn't at the
   * starting location with no crumbs. If there are crumbs left, the last crumb is picked up and the user's location 
   * is set to the previous location.
   */
  
  // reference to the last element pushed on the stack
  Breadcrumb top;
  
  public BreadcrumbTrail() {    
    //creates an empty stack
    this.top = null;    
  }
  
  /**
   * Decreases top by 1 to go back to the previous locale.
   */
  // Pop  
  public void pickupCrumb() {
    this.top = this.top.link;
  }
   
  
  /**
   * Increases top by 1 to track the new locale.
   * @param x
   */
  // Push
  public void dropCrumb(int x) {
    Breadcrumb stack = new Breadcrumb(x, this.top);
    this.top = stack;
  }


  /**
   * @param User
   * @return - Returns the position of the current crumb.
   */
  // Peek / top
   public int currentCrumb() {
       return this.top.data;
   }
   
  /**
   * Checks to see if there are more crumbs / another locale in the trail.
   * @return - Returns true if there is another bread crumb.
   */
  // isEmpty
   public Boolean hasMoreCrumbs() {     
     if (this.top.link == null) {
       return false; // no more crumbs
     } else {
       return true; // there are more crumbs
     }
  }  
  
  /**
   * @param User
   * @param stack
   * @param data
   * @param top
   * @param locations
   * If there are more crumbs, the current crumb is picked up, then the next crumb in the trail
   * becomes the user's current location. Finally, the user is alerted where they now are.
   */
  void goBack(Player User, BreadcrumbTrail stack, Locale[] locations) {
    // there are more crumbs
    if (stack.hasMoreCrumbs() == true) {      
      // pick up the current crumb
      stack.pickupCrumb();
      // set the current location to the previous locations and alert the user.
      User.location = stack.currentCrumb(); 
      System.out.println(locations[User.location]);
      
    // no crumbs left
    } else if (stack.hasMoreCrumbs() == false){
      stack.dropCrumb(User.location);
      System.out.println(locations[User.location]);
    }
  }
}


// represents a node
class Breadcrumb {
  int data;
  Breadcrumb link;
  
  Breadcrumb(int x, Breadcrumb n) {
    this.data = x;
    this.link = n;
  }
}