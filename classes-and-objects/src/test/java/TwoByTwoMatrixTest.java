import static org.junit.Assert.*;

import all.TwoByTwoMatrix;
import org.junit.Test;

public class TwoByTwoMatrixTest {

  @Test
  public void testInverse() {
    TwoByTwoMatrix a = new TwoByTwoMatrix(4, 4, 2, 2);
    TwoByTwoMatrix b = new TwoByTwoMatrix(1, 2, 3, 4);
    TwoByTwoMatrix bInverse = new TwoByTwoMatrix(-2, 1, 1.5, -0.5);
    assertTrue(b.inverse().equals(bInverse));
  }
}
