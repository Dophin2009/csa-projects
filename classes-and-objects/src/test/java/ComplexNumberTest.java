import all.ComplexNumber;
;
import org.junit.Assert;
import org.junit.Test;

public class ComplexNumberTest {

  private final double THRESHOLD = 0.0001;

  @Test
  public void testEquals() {
    ComplexNumber a = new ComplexNumber(3.23098, -42.23098);
    ComplexNumber b = new ComplexNumber(3.230987, -42.230986);
    Assert.assertEquals(a, b);
  }

  @Test
  public void testNotEquals() {
    ComplexNumber a = new ComplexNumber(1, 1);
    ComplexNumber b = null;
    Assert.assertNotEquals(a, b);
  }

  @Test
  public void testNotEqualsWithWrongType() {
    ComplexNumber a = new ComplexNumber(1, 1);
    Object b = new Object();
    Assert.assertNotEquals(a, b);
  }

  @Test
  public void testToString() {
    ComplexNumber a = new ComplexNumber(5.52093, -6.12039);
    String aString = "5.52-6.12i";
    Assert.assertEquals(a.toString(), aString);
  }

  @Test
  public void testPlus() {
    ComplexNumber a = new ComplexNumber(12, -3);
    ComplexNumber b = new ComplexNumber(-4, 5.6);
    ComplexNumber sum = new ComplexNumber(8, 2.6);
    Assert.assertEquals(a.plus(b), sum);
  }

  @Test
  public void testTimesReal() {
    ComplexNumber a = new ComplexNumber(4, 2);
    double factor = 16;
    ComplexNumber aTimes = new ComplexNumber(64, 32);
    Assert.assertEquals(a.times(factor), aTimes);
  }

  @Test
  public void testTimesComplex() {
    ComplexNumber a = new ComplexNumber(2, -3);
    ComplexNumber b = new ComplexNumber(3, -1);
    ComplexNumber product = new ComplexNumber(3, -11);
    Assert.assertEquals(a.times(b), product);
  }

  @Test
  public void testDividedByReal() {
    ComplexNumber a = new ComplexNumber(-5, 3);
    double divisor = -6;
    ComplexNumber aDivided = new ComplexNumber(5.0 / 6.0, -1.0 / 2.0);
    Assert.assertEquals(a.dividedBy(divisor), aDivided);
  }

  @Test
  public void testDividedByComplexEquals() {
    ComplexNumber a = new ComplexNumber(4, 2);
    ComplexNumber b = new ComplexNumber(2, 3);
    ComplexNumber quotient = new ComplexNumber(14.0 / 13.0, -8.0 / 13.0);
    Assert.assertEquals(a.dividedBy(b), quotient);
  }

  @Test
  public void testPowReal() {
    ComplexNumber a = new ComplexNumber(4, 3);
    double exponent = 2.0 / 3.0;
    ComplexNumber aPow = new ComplexNumber(2.65904868, 1.21628114);
    Assert.assertEquals(a.pow(exponent), aPow);
  }

  @Test
  public void testPowComplex() {
    ComplexNumber a = new ComplexNumber(1, 1);
    ComplexNumber exponent = new ComplexNumber(1, 1);
    ComplexNumber aPow = new ComplexNumber(0.27395725, 0.58370076);
    Assert.assertEquals(a.pow(exponent), aPow);
  }

  @Test
  public void testGetConjugate() {
    ComplexNumber a = new ComplexNumber(3, -4);
    ComplexNumber aConjugate = new ComplexNumber(3, 4);
    Assert.assertEquals(a.getConjugate(), aConjugate);
  }

  @Test
  public void testGetModulus() {
    ComplexNumber a = new ComplexNumber(7, 24);
    double aModulus = 25;
    Assert.assertTrue(Math.abs(a.getModulus() - aModulus) < THRESHOLD);
  }

  @Test
  public void testGetReal() {
    ComplexNumber a = new ComplexNumber(6, 3);
    double aReal = 6;
    Assert.assertTrue(Math.abs(a.getReal() - aReal) < THRESHOLD);
  }

  @Test
  public void testGetImaginary() {
    ComplexNumber a = new ComplexNumber(4, 3);
    double aImaginary = 3;
    Assert.assertTrue(Math.abs(a.getImaginary() - aImaginary) < THRESHOLD);
  }
}
