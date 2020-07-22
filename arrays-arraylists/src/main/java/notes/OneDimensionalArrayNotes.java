package notes;

public class OneDimensionalArrayNotes {

  public static void main(String[] args) {
    int[] a = new int[] {1, 2, 5};
    int[] b = new int[] {2, 5, 7, 3, 5, 2, 8};

    sort(b);

    System.out.println(toString(b));
  }

  public static String toString(int[] a) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < a.length; i++) {
      s.append(a[i]);
      if (i != a.length - 1) {
        s.append(" ");
      }
    }
    return s.toString();
  }

  public static int sum(int[] a) {
    int sum = 0;
    for (int i : a) {
      sum += i;
    }
    return sum;
  }

  public static void sort(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      for (int j = i + 1; j < a.length - 1; j++) {
        if (a[i] > a[j]) {
          swap(a, i, j);
        }
      }
    }
  }

  public static double average(int[] a) { return (double)sum(a) / a.length; }

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public static int min(int[] a) {
    int min = a[0];
    for (int i : a) {
      if (i < min) {
        min = i;
      }
    }
    return min;
  }

  public static int max(int[] a) {
    int max = a[0];
    for (int i : a) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  public static void reverse(int[] a) {
    for (int i = 0; i < a.length; i++) {
      int temp = a[i];
      a[i] = a[a.length - 1];
      a[a.length - 1] = temp;
    }
  }

  public static void shift(int[] a) {
    int temp = a[0];
    for (int i = 0; i < a.length; i++) {
      try {
        a[i] = a[i + 1];
      } catch (ArrayIndexOutOfBoundsException ignored) {
      }
    }
    a[a.length - 1] = temp;
  }
}
