package matrixGraph;

import org.junit.Assert;
import org.junit.Test;

public class SimpleGraphTest {

  @Test
  public void arcsGetAddedTest() {
    SimpleGraph test = getSimpleGraph();
    Assert.assertTrue(test.hasArc(2, 1));
    Assert.assertTrue(test.hasArc(5, 4));
  }

  @Test
  public void getNodeDegreeTest() {
    SimpleGraph test = getSimpleGraph();
    Assert.assertEquals(1, test.getNodeDegree(1));
    Assert.assertEquals(2, test.getNodeDegree(3));
  }

  @Test
  public void toStringTest() {
    SimpleGraph test = getSimpleGraph();
    String expectedOutput = "1 -> 2\n" +
            "2 -> 1\n" +
            "3 -> 4 -> 5\n" +
            "4 -> 3 -> 5\n" +
            "5 -> 3 -> 4";
    Assert.assertEquals(expectedOutput, test.toString());
  }

  @Test
  public void eulerCircuitTest() {
    SimpleGraph test2 = getEulerCircuit();
    Assert.assertTrue(SimpleGraphAlgorithm.hasEulerCycle(test2));
  }


  private SimpleGraph getSimpleGraph() {
    SimpleGraph test = new SimpleGraph(5);
    test.addArc(1, 2);
    test.addArc(3, 4);
    test.addArc(4, 5);
    test.addArc(3, 5);
    return test;
  }

  private SimpleGraph getEulerCircuit() {
    SimpleGraph eulerGraph = new SimpleGraph(4);
    eulerGraph.addArc(1, 2);
    eulerGraph.addArc(2, 3);
    eulerGraph.addArc(3, 4);
    eulerGraph.addArc(4, 1);
    return eulerGraph;
  }
}
