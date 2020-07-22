package all;

import java.util.ArrayList;
import java.util.List;

public class MandelbrotSet {

  private List<ComplexNumber> set;

  /**
   * No parameter constructor
   * Set will be based on (-2, 1) in the real plane and (-1, 1) in the imaginary
   * plane.
   */
  public MandelbrotSet() {
    set = new ArrayList<ComplexNumber>();
    generateSet(-2.0, 1.0, 0.03, -1.0, 1.0, 0.03);
  }

  public MandelbrotSet(double lowR, double highR, double realInterval,
                       double lowI, double highI, double imagInterval) {
    set = new ArrayList<ComplexNumber>();
    generateSet(lowR, highR, realInterval, lowI, highI, imagInterval);
  }

  public void generateSet(double lr, double hr, double ri, double li, double hi,
                          double ii) {
    for (double real = lr; real < hr; real += ri) {
      for (double imag = li; imag < hi; imag += ii) {
        ComplexNumber c = new ComplexNumber(real, imag);
        if (isInSet(c)) {
          set.add(c);
        }
      }
    }
  }

  public boolean isInSet(ComplexNumber c) {
    ComplexNumber z0 = new ComplexNumber();
    ComplexNumber z1 = new ComplexNumber();
    int maxIterations = 100;
    for (int i = 0; i < maxIterations; i++) {
      z1 = z0.times(z0).plus(c);
      z0 = z1;
    }
    return z1.getModulus() <= 2;
  }

  public List<ComplexNumber> getSet() { return set; }
}
