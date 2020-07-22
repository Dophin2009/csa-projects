public class Range implements NumberGroup {

  private int[] range;

  public Range(int low, int high) {
    range = new int[high - low + 1];
    for (int i = low; i <= high; i++) {
      range[i - low] = i;
    }
  }

  @Override
  public boolean contains(int x) {
    for (int i = 0; i < range.length; i++) {
      if (range[i] == x) {
        return true;
      }
    }
    return false;
  }
}
