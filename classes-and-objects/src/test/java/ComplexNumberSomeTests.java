import static org.junit.Assert.*;

import all.ComplexNumber;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mr. Sharick
 *
 */
public class ComplexNumberSomeTests {

  ComplexNumber a, b, c, d, zer, e, f, z, y, aPlusb, aPlusd, aTimes5, aPlusZer,
      aTimesb, aDivideb, aDividezer, conja, conjb, conjc, conjd, aSquared,
      bCubed, zerFourth, zPowy;

  double modulusa;
  String aString, bString, cString, dString, zerString, eString, fString;

  @Before
  public void setUp() {
    a = new ComplexNumber(7, 8);
    aString = "7.00+8.00i";
    conja = new ComplexNumber(7, -8);

    b = new ComplexNumber(-5, 4);
    bString = "-5.00+4.00i";
    conjb = new ComplexNumber(-5, -4);

    c = new ComplexNumber(3, -2);
    cString = "3.00-2.00i";
    conjc = new ComplexNumber(3, 2);

    d = new ComplexNumber(-2, -3);
    dString = "-2.00-3.00i";
    conjd = new ComplexNumber(-2, 3);

    e = new ComplexNumber(14, 16);

    zer = new ComplexNumber();
    zerString = "0.00+0.00i";

    z = new ComplexNumber(3, -4);
    y = new ComplexNumber(1, 4);
    aPlusb = new ComplexNumber(2, 12);
    aPlusd = new ComplexNumber(5, 5);
    aPlusZer = new ComplexNumber(7, 8);

    aTimesb = new ComplexNumber(-167, -12);
    aTimes5 = new ComplexNumber(35, 40);

    aDivideb = new ComplexNumber(-0.073, -1.659);

    aSquared = new ComplexNumber(-15, 112);
    bCubed = new ComplexNumber(115, 236);
    zerFourth = new ComplexNumber(0, 0);

    modulusa = Math.sqrt(113);

    zPowy = new ComplexNumber(146, -142);
  }
  /**
   * Tests toString()
   */
  @Test
  public void testToString() {
    assertEquals(aString, a.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideZeroExpt() {
    a.dividedBy(zer);
  }

  @Test
  public void testComplexNumberDoubleDouble() {
    assertTrue(a.getReal() == 7);
    assertTrue(a.getImaginary() == 8);
  }
  @Test
  public void testComplexNumber() {
    assertTrue(zer.getReal() == 0 && zer.getImaginary() == 0);
  }

  @Test
  public void testPlus() {
    assertEquals(aPlusb, a.plus(b));
  }

  @Test
  public void testTimesDouble() {
    assertEquals(e, a.times(2));
  }

  @Test
  public void testDividedBy() {
    assertEquals(aDivideb.getReal(), a.dividedBy(b).getReal(), .001);
  }

  @Test
  public void testGetModulus() {
    assertEquals(modulusa, a.getModulus(), 0.000001);
  }

  @Test
  public void testEqualsObject() {
    assertEquals(a, new ComplexNumber(7, 8));
  }
}
