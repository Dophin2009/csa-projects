package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingHat {

  public static List<Object[]> sortSteps = new ArrayList<>();

  public static <T> int linearSearch(T[] a, T key) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == key) {
        return i;
      }
    }
    return -1;
  }

  public static <T extends Comparable<T>> int binarySearch(T[] a, T key) {
    return binarySearch(a, key, 0, a.length - 1);
  }

  private static <T extends Comparable<T>> int binarySearch(T[] a, T key,
                                                            int low, int high) {
    if (low >= high) {
      return a[low] == key ? low : -1;
    }

    int m = (high + low) / 2;
    if (a[m].compareTo(key) == 0) {
      return m;
    }

    if (a[m].compareTo(key) > 0) {
      return binarySearch(a, key, low, m - 1);
    } else {
      return binarySearch(a, key, m + 1, high);
    }
  }

  public static <T extends Comparable<T>> void bubbleSort(T[] a) {
    for (int p = a.length; p > 0; p--) {
      for (int i = 1; i < p; i++) {
        if (a[i].compareTo(a[i - 1]) < 0) {
          swap(a, i, i - 1);
        }
      }
    }
  }

  public static <T extends Comparable<T>> void selectionSort(T[] a) {
    for (int k = 0; k < a.length; k++) {
      int min = k;
      for (int i = k; i < a.length; i++) {
        if (a[i].compareTo(a[min]) < 0) {
          min = i;
        }
      }
      swap(a, k, min);
    }
  }

  public static <T extends Comparable<T>> void insertionSort(T[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (a[j].compareTo(a[j - 1]) < 0) {
          swap(a, j, j - 1);
        }
      }
    }
  }

  public static <T extends Comparable<T>> void quickSort(T[] a, int low,
                                                         int high) {
    int i = low;
    int j = high;
    if (i >= j) {
      return;
    }

    if (i == j - 1) {
      if (a[i].compareTo(a[j]) > 0) {
        swap(a, i, j);
      }
      return;
    }

    int pivot = i;
    while (i != j) {
      while (a[j].compareTo(a[pivot]) > 0 && i != j) {
        j--;
      }
      while (a[i].compareTo(a[pivot]) <= 0 && i != j) {
        i++;
      }
      if (i == j) {
        swap(a, i, pivot);
      } else {
        swap(a, i, j);
      }
    }

    quickSort(a, low, i - 1);
    quickSort(a, i + 1, high);
  }

  public static void heapSort(Integer[] a) {
    Integer[] heap = new Integer[a.length + 1];
    System.arraycopy(a, 0, heap, 1, a.length);
    int last = heap.length - 1;
    heapify(heap, last);
    for (int i = last; i > 0; i--) {
      swap(heap, 1, i);
      last--;

      maxHeap(heap, 1, last);
    }

    System.arraycopy(heap, 1, a, 0, a.length);
  }

  private static void heapify(Integer[] heap, int last) {
    for (int i = last / 2; i > 0 / 2; i--) {
      maxHeap(heap, i, last);
    }
  }

  private static void maxHeap(Integer[] heap, int i, int last) {
    int left = 2 * i;
    int right = 2 * i + 1;

    int max = i;
    if (right <= last && heap[right] > heap[i]) {
      max = right;
    }
    if (left <= last && heap[left] > heap[max]) {
      max = left;
    }

    if (max != i) {
      swap(heap, i, max);
      maxHeap(heap, max, last);
    }
  }

  //    public static <T extends Comparable<T>> void mergeSort(T[] a, int low,
  //    int high) {
  //        if (low == high - 1) {
  //            if (a[low].compareTo(a[high]) > 0) {
  //                swap(a, low, high);
  //            }
  //            return;
  //        }
  //
  //        mergeSort(a, low, (low + high) / 2);
  //        mergeSort(a, (low + high) / 2 + 1, high);
  //
  //        merge(a, low, (low + high) / 2, high);
  //
  //    }
  //
  //    public static <T extends Comparable<T>> void merge(T[] a, int low, int
  //    middle, int high) {
  //        T[] lowHalf = (T[]) Array.newInstance(a.getClass(), middle - low +
  //        1); T[] highHalf = (T[]) Array.newInstance(a.getClass(), high -
  //        lowHalf.length);
  //
  //        for (int i = 0; i < a.length; i++) {
  //            if (i < lowHalf.length) {
  //                lowHalf[i] = a[i];
  //            } else {
  //                highHalf[i - lowHalf.length] = a[i];
  //            }
  //        }
  //
  //        int i = 0;
  //        int j = 0;
  //        for (int k = low; k < high; k++) {
  //            if (i == middle || lowHalf[i].compareTo(highHalf[j]) <= 0) {
  //                a[k] = lowHalf[i];
  //                i++;
  //            } else if (j == high || lowHalf[i].compareTo(highHalf[j]) > 0) {
  //                a[k] = highHalf[j];
  //                j++;
  //            }
  //        }
  //
  //    }

  public static void swap(Object[] a, int p, int q) {
    Object temp = a[p];
    a[p] = a[q];
    a[q] = temp;
    Object[] b = new Object[a.length];
    System.arraycopy(a, 0, b, 0, a.length);
    sortSteps.add(b);
    sortSteps.add(Arrays.copyOf(a, a.length));
  }

  public static Integer[] generateRandomIntegerArray(int numTerms, int max) {
    Integer[] list = new Integer[numTerms];
    for (int i = 0; i < numTerms; i++) {
      list[i] = (int)(Math.random() * max);
    }
    return list;
  }
}
