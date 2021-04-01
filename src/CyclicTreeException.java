public class CyclicTreeException extends Exception{
  @Override
  public String getMessage() {
    return "A cycle in the graph detected.";
  }
}
