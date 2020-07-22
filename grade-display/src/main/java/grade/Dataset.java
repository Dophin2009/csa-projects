package grade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Dataset {

  private double[] values;

  /**
   * Constructor to read a file of scores and store then in <code>values</code>.
   * @param filename - path to a file containing data values.
   */
  public Dataset(String filename) {

    File f = new File(filename);

    int count = 0;
    try {
      Scanner reader = new Scanner(f);
      while (reader.hasNextDouble()) {
        reader.nextDouble();
        count++;
      }

      if (count == 0) {
        throw new IllegalArgumentException("No data found.");
      }

      reader = new Scanner(f);
      values = new double[count];
      for (int i = 0; i < count; i++) {
        values[i] = reader.nextDouble();
      }
      Arrays.sort(values);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructor to set <code>values</code> to an array of zero values.
   */
  public Dataset() { values = new double[0]; }

  /**
   * Method to calculate the range of the dataset.
   * @return the range of the dataset.
   */
  public double range() { return max() - min(); }

  /**
   * Method to find the maximum value in the dataset.
   * @return the maximum value in the dataset.
   */
  public double max() {
    double max = values[0];
    for (double g : values) {
      if (g > max) {
        max = g;
      }
    }
    return max;
  }

  /**
   * Method to find the minimum value in the dataset.
   * @return the minimum value in the dataset.
   */
  public double min() {
    double min = values[0];
    for (double g : values) {
      if (g < min) {
        min = g;
      }
    }
    return min;
  }

  /**
   * Method to calculate the mean of the dataset.
   * @return the mean of the dataset.
   */
  public double mean() {
    double sum = 0;
    for (double g : values) {
      sum += g;
    }
    return sum / values.length;
  }

  /**
   * Method to calculate the median of the dataset.
   * @return the median of the dataset.
   */
  public double median() {
    if (values.length % 2 == 0) {
      return (values[values.length / 2] + values[values.length / 2 - 1]) / 2;
    } else {
      return values[values.length / 2];
    }
  }

  /**
   * Method to find the mode of the dataset.
   * @return the mode of the dataset.
   */
  public double[] mode() {
    double[] modes = new double[1];
    int modeCount = 0;
    for (double a : values) {
      int count = 0;
      for (double b : values) {
        if (a == b) {
          count++;
        }
      }
      if (count > modeCount) {
        modes = new double[] {a};
        modeCount = count;
      } else if (count == modeCount) {
        boolean inSet = false;
        for (double m : modes) {
          if (a == m) {
            inSet = true;
          }
        }
        if (!inSet) {
          double[] temp = new double[modes.length + 1];
          for (int i = 0; i < modes.length; i++) {
            temp[i] = modes[i];
          }
          temp[temp.length - 1] = a;
          modes = temp;
        }
      }
    }
    return modes;
  }

  /**
   * Method to calculate the variance of the dataset.
   * @return the variance of the dataset.
   */
  public double variance() {
    double s = 0;
    for (double g : values) {
      s += Math.pow(g - mean(), 2);
    }
    return s / values.length;
  }

  /**
   * Method to calculate the standard deviation of the dataset.
   * @return the stardard deviation of the dataset.
   */
  public double standardDeviation() { return Math.pow(variance(), 0.5); }

  public Section[] histogram(int sizeOfBucket, int minValue, int maxValue) {
    Section[] sections =
        new Section[(maxValue - (minValue - 1)) / sizeOfBucket];
    for (int i = 0; i < sections.length; i++) {
      sections[i] = new Section(0, i * sizeOfBucket, (i + 1) * sizeOfBucket);
    }

    for (double g : values) {
      for (Section s : sections) {
        if (g > s.getLowerBound() && g <= s.getUpperBound()) {
          s.addSetValue(g);
        }
      }
    }
    return sections;
  }

  /**
   * Method to add a new data value to the data set.
   * @param newValue - the data value to be added to the set.
   */
  public void addValue(double newValue) {
    double[] newSet = new double[values.length + 1];
    for (int i = 0; i < values.length; i++) {
      newSet[i] = values[i];
    }
    newSet[newSet.length - 1] = newValue;
    values = newSet;
  }

  public double[] getValues() { return values; }
}
