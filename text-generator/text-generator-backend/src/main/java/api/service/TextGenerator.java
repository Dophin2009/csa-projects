package api.service;

import java.io.*;
import java.util.*;

public class TextGenerator {

  private Map<String, List<String>> map;

  public TextGenerator() {
    map = new HashMap<>();
    File dir = new File("src/main/resources/texts");
    for (File f : Objects.requireNonNull(dir.listFiles())) {
      updateMap(textToList(f));
    }
  }

  public String getRandomSentence(int length) {
    StringBuilder s = new StringBuilder();

    String w = getRandomFirstWord();
    for (int i = 0; i < length; i++) {
      s.append(w).append(" ");
      w = nextWord(w);
    }
    return s.toString();
  }

  public String getRandomSentence() {
    StringBuilder s = new StringBuilder();
    int length = 0;

    while (length < 7) {
      String w = getRandomFirstWord();
      while (w != null) {
        s.append(w).append(" ");
        length++;

        char last = w.charAt(w.length() - 1);
        if (last == '.' || last == '!' || last == '?') {
          break;
        }

        w = nextWord(w);
      }
    }

    return s.toString();
  }

  private String nextWord(String s) {
    List<String> pos = map.get(s);
    if (pos == null) {
      return null;
    }
    return pos.get((int)(pos.size() * Math.random()));
  }

  private String getRandomFirstWord() {
    Object[] keys = map.keySet().toArray();
    return (String)keys[(int)(Math.random() * keys.length)];
  }

  private List<String> textToList(File f) {
    StringBuilder s = new StringBuilder();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String line = reader.readLine();

      while (line != null) {
        s.append(line).append("\n");
        line = reader.readLine();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    String regex = "([\\s|()\\[\\]\\n])";
    // String regex = "([.,!?:;|\\-\\[\\]\\(\\)\\s\\t])";
    String[] words = s.toString().split(regex);
    return new ArrayList<>(Arrays.asList(words));
  }

  private void updateMap(List<String> words) {
    for (int i = 0; i < words.size() - 1; i++) {
      String w = words.get(i);
      String fol = words.get(i + 1);

      if (w.trim().length() != 0 && fol.trim().length() != 0) {
        if (!map.containsKey(w)) {
          List<String> following = new ArrayList<>();
          following.add(fol);
          map.put(w, following);
        } else {
          map.get(w).add(fol);
        }
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();

    Object[] keys = map.keySet().toArray();
    for (Object k : keys) {
      res.append(k)
          .append(": ")
          .append(map.get((String)k).toString())
          .append("\n");
    }
    return res.toString();
  }
}
