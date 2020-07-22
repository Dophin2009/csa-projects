package grade;

/**
 * A class that represents a section of the data set.
 */
public class Section {

  private Dataset dataset;

  private int value;
  private double lowerBound, upperBound;

  /**
   * Constructor.
   * @param value - the number of data values within this section of the set.
   * @param lowerBound - the lower bound of this section.
   * @param upperBound - the upper bound of this section.
   */
  public Section(int value, double lowerBound, double upperBound) {
    this.value = value;
    dataset = new Dataset();
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * Method to return the number of data values within this section of the set.
   * @return a number representing the number of data values within this section
   *     of the set.
   */
  public int getValue() { return value; }

  /**
   * Method to return the lower bound of this section.
   * @return a number representing the lower bound of this section.
   */
  public double getLowerBound() { return lowerBound; }

  /**
   * Method to return the upper bound of this section.
   * @return a number representing the upper bound of this section.
   */
  public double getUpperBound() { return upperBound; }

  /**
   * Method to return the set of data of this section.
   * @return the <code>Dataset</code> contained within this section.
   */
  public Dataset getDataset() { return dataset; }

  /**
   * Method add a new value to the <code>Dataset</code> and increment
   * <code>value</code> by 1.
   * @param newValue - the data value to be added to the set.
   */
  public void addSetValue(double newValue) {
    dataset.addValue(newValue);
    value++;
  }
}
