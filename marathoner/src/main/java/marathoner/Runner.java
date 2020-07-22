package marathoner;

public class Runner extends Athlete {

  private int numberOfRaces;
  private double milesRaced;

  public Runner(String firstName, String lastName) {
    super(firstName, lastName, "Running");
    numberOfRaces = 0;
    milesRaced = 0;
  }

  public void race(double raceLength) {
    System.out.println(getName() + " is racing in a " + raceLength +
                       " mile race.");
    numberOfRaces++;
    milesRaced += raceLength;
  }

  public int getNumberOfRaces() { return numberOfRaces; }

  public double getMilesRaced() { return milesRaced; }
}
