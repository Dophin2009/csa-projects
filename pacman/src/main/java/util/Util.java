package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

  private Util() {}

  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    BigDecimal d = BigDecimal.valueOf(value);
    d = d.setScale(places, RoundingMode.HALF_UP);
    return d.doubleValue();
  }
}
