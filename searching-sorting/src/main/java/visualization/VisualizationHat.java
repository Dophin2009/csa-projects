package visualization;

import processing.core.PApplet;
import sorting.SortingHat;

public class VisualizationHat extends PApplet {

  public static void main(String[] args) {
    PApplet.main(VisualizationHat.class);
  }

  public void settings() { size(600, 600); }

  public void setup() {
    Integer[] nums = SortingHat.generateRandomIntegerArray(50, 100);
    SortingHat.heapSort(nums);
  }

  int i = 0;
  public void draw() {
    if (i < SortingHat.sortSteps.size()) {
      background(255);
      Object[] current = (Object[])SortingHat.sortSteps.get(i);
      for (int j = 0; j < current.length; j++) {
        rect(100 + j * 10, 400 - (Integer)current[j] * 3, 5,
             (Integer)current[j] * 3);
      }
    } else {
      noLoop();
    }
    i++;
  }
}
