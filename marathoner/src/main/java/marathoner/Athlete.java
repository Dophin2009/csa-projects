package marathoner;

public class Athlete {

  private String firstName, lastName;
  private String sport;
  private double hoursTraining;

  public Athlete(String firstName, String lastName, String sport) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.sport = sport;
  }

  public void train(double hours) {
    System.out.println("Athlete training for " + hours + " hours.");
    hoursTraining += hours;
  }

  public String getName() { return firstName + " " + lastName; }

  public String getSport() { return sport; }

  public double getHoursTraining() { return hoursTraining; }
}
