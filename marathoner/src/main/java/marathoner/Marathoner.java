package marathoner;

public class Marathoner extends Runner {

  public Marathoner(String firstName, String lastName) {
    super(firstName, lastName);
  }

  @Override
  public void race(double raceLength) {
    if (raceLength >= 10) {
      super.race(raceLength);
    } else {
      super.train(8.5 * raceLength);
    }
  }
}
