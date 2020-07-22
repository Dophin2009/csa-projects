package all;

import java.util.Arrays;

public class Triangle {

  private double sideA, sideB, sideC;
  private double[] sortedSides;

  public Triangle(double sideA, double sideB, double sideC) {
    if (!(sideA + sideB > sideC && sideA + sideC > sideB &&
          sideB + sideC > sideA) ||
        (sideA != 0 && sideB != 0 && sideC == 0)) {
      throw new IllegalArgumentException("Not a valid set of sides.");
    } else {
      this.sideA = sideA;
      this.sideB = sideB;
      this.sideC = sideC;

      sortedSides = new double[] {sideA, sideB, sideC};
      Arrays.sort(sortedSides);
    }
  }

  // Triangles are equal if they have the same side lengths and thus are
  // congruent.
  public boolean equals(Object other) { return isCongruent((Triangle)other); }

  public boolean isCongruent(Triangle other) {
    double[] otherSides = {other.getSideA(), other.getSideB(),
                           other.getSideC()};
    Arrays.sort(sortedSides);
    Arrays.sort(otherSides);
    for (int i = 0; i < sortedSides.length; i++) {
      if (sortedSides[i] != otherSides[i]) {
        return false;
      }
    }
    return true;
  }

  public boolean isAnIsoscelesTriangle() {
    return (sortedSides[0] == sortedSides[1] ||
            sortedSides[1] == sortedSides[2]) &&
        sortedSides[0] != sortedSides[2];
  }

  public boolean isARightTriangle() {
    return Math.pow(sortedSides[0], 2) + Math.pow(sortedSides[1], 2) ==
        Math.pow(sortedSides[2], 2);
  }

  public double getArea() {
    double s = 1 / 2 * (sideA + sideB + sideC);
    return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
  }

  public double getSideA() { return sideA; }

  public double getSideB() { return sideB; }

  public double getSideC() { return sideC; }
}
