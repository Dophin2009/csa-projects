package all;

public class Time {

  private int hours;
  private int mins;

  public Time(int hours, int mins) {
    if (hours > 23 || hours < 0) {
      throw new IllegalArgumentException("Not valid number of hours.");
    }
    if (mins > 59 || mins < 0) {
      throw new IllegalArgumentException("Not valid number of minutes.");
    }
    this.hours = hours;
    this.mins = mins;
  }

  private int toMins() { return hours * 60 + mins; }

  public boolean lessThan(Time t) { return t.toMins() > toMins(); }

  public int elapsedSince(Time t) { return toMins() - t.toMins(); }

  public String toString() {
    String hoursPlaceZero = hours < 10 ? "0" : "";
    String minsPlaceZero = mins < 10 ? "0" : "";
    return hoursPlaceZero + hours + ":" + minsPlaceZero + mins;
  }
}
