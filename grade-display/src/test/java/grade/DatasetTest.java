package grade;

import org.junit.Assert;
import org.junit.Test;

public class DatasetTest {

  private Dataset dt = new Dataset("scores.txt");

  @Test
  public void testMin() {
    Assert.assertEquals(32.0, dt.min(), 0.0);
  }

  @Test
  public void testMax() {
    Assert.assertEquals(97.0, dt.max(), 0.0);
  }

  @Test
  public void testMedian() {
    Assert.assertEquals(77.5, dt.median(), 0.0);
  }

  @Test
  public void testMean() {
    Assert.assertEquals(75.9, dt.mean(), 0.01);
  }

  @Test
  public void testMode() {
    Assert.assertArrayEquals(new double[] {83.0, 84.0}, dt.mode(), 0.01);
  }

  @Test
  public void testVariance() {
    Assert.assertEquals(140.49, dt.variance(), 0.01);
  }

  @Test
  public void testStandardDeviation() {
    Assert.assertEquals(11.85, dt.standardDeviation(), 0.01);
  }

  @Test
  public void testHistogramLength() {
    Assert.assertEquals(dt.histogram(10, 1, 100).length, 10);
  }

  @Test
  public void testHistogram() {
    Section[] sections = dt.histogram(10, 1, 100);
    int[] values = new int[] {0, 0, 0, 1, 0, 4, 16, 13, 25, 5};
    for (int i = 0; i < sections.length; i++) {
      Assert.assertEquals(sections[i].getValue(), values[i]);
    }
  }
}
