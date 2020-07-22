package ie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IBeforeE {

  public static void main(String[] args) {
    List<String> words = new ArrayList<>();

    try {
      Scanner reader =
          new Scanner(new FileReader(new File("src/main/resources/words.txt")));
      while (reader.hasNext()) {
        words.add(reader.next());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String[] checked = words.stream()
                           .filter(w -> w.contains("ie") || w.contains("ei"))
                           .toArray(String[] ::new);
    String[] match = Arrays.stream(checked)
                         .filter(w -> {
                           int ie = w.indexOf("ie");
                           if (ie == -1) {
                             int ei = w.indexOf("ei");
                             return ei == 0 || w.charAt(ei - 1) == 'c';
                           } else {
                             return ie == 0 || w.charAt(ie - 1) != 'c';
                           }
                         })
                         .toArray(String[] ::new);

    System.out.println(
        String.format("%f%%", (double)match.length / checked.length * 100));
  }
}
