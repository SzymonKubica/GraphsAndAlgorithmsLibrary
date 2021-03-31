public class SimpleGraphDemo {
  public static void main(String[] args) {
    SimpleGraph test = new SimpleGraph(5);
    test.addArc(1, 2);
    test.addArc(3, 4);
    test.addArc(4, 5);
    test.addArc(3, 5);

    assert test.hasArc(2,1);
    assert test.hasArc(5,4);

    System.out.println(test);
  }
}
