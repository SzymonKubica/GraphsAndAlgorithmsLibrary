package Algorithms;

public class ItemNotFoundException extends Exception{
  @Override
  public String getMessage() {
    return "Item was not found in the list";
  }
}
