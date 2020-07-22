package com.elusivedevelopment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ArrayListManipulation {

  private List<String> words;

  /**
   * One parameter constructor to read a file and store words within an
   * ArrayList <code>words</code>.
   * @param filename - name of the file being read.
   */
  public ArrayListManipulation(String filename) {
    words = new ArrayList<>();
    try {
      Scanner reader = new Scanner(new FileReader(filename));
      while (reader.hasNext()) {
        words.add(reader.next());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method to print words in <code>words</code> ten per line in the console.
   */
  public void printTenWordsPerLine() {
    for (int i = 0; i < words.size(); i++) {
      System.out.print(words.get(i));
      if (i % 10 == 9) {
        System.out.print("\n");
      } else {
        System.out.print(" ");
      }
    }
  }

  /**
   * Method to return the number of words in <code>words</code>.
   * @return an integer representing the number of words.
   */
  public int numWords() { return words.size(); }

  /**
   * Method to return the longest word in <code>words</code>.
   * @return a String representing the longest word in <code>words</code>.
   */
  public String longestWord() {
    if (words.isEmpty()) {
      return null;
    }

    String longest = words.get(0);
    for (String w : words) {
      if (w.length() > longest.length()) {
        longest = w;
      }
    }
    return longest;
  }

  /**
   * Method to get the index of the given word in <code>words</code>.
   * @param word - the word to be searched for in <code>words</code>.
   * @return  an integer representing the index of the given word in
   *     <code>words</code> and -1 if the word is not
   *          in <code>words</code>.
   */
  public int indexOfWord(String word) {
    for (String w : words) {
      if (word.equalsIgnoreCase(w)) {
        return words.indexOf(w);
      }
    }
    return -1;
  }

  /**
   * Method to get the number of instances of the given word in
   * <code>words</code>.
   * @param word - the word to be searched for in <code>words</code>.
   * @return an integer representing the number of instances of the given word
   *     in <code>words</code>.
   */
  public int countWord(String word) {
    int count = 0;
    for (String w : words) {
      if (word.equalsIgnoreCase(w)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Method to get an <code>ArrayList</code> that contains all the words in
   * <code>words</code> starting with the given character.
   * @param c - the character the returned <code>ArrayList</code>'s elements
   *     must start with.
   * @return  a new <code>ArrayList</code> containing all the words within
   *     <code>words</code> that start with the
   *          given character.
   */
  public ArrayList<String> startsWithLetter(char c) {
    ArrayList<String> matches = new ArrayList<>();
    for (String w : words) {
      if (Character.toLowerCase(w.charAt(0)) == Character.toLowerCase(c)) {
        matches.add(w);
      }
    }
    return matches;
  }

  /**
   * Method to return an <code>ArrayList</code> containing the elements of
   * <code>words</code> in alphabetical order.
   * @return a new <code>ArrayList</code> containing the elements of
   *     <code>words</code> in alphabetical order.
   */
  public ArrayList<String> alphabetize() {
    ArrayList<String> alphabetized = new ArrayList<>(words.size());
    alphabetized.addAll(words);
    alphabetized.sort(new StringAlphabetize());
    return alphabetized;
  }

  /**
   * Method to return the first word in <code>words</code> alphabetized.
   * @return a String representing the first word in <code>words</code>
   *     alphabetized.
   */
  public String max() { return words.isEmpty() ? null : alphabetize().get(0); }

  /**
   * Method to return the last word in <code>words</code> alphabetized.
   * @return a String representing the last word in <code>words</code>
   *     alphabetized.
   */
  public String min() {
    return words.isEmpty() ? null : alphabetize().get(alphabetize().size() - 1);
  }

  /**
   * Method to return an <code>ArrayList</code> of the words of
   * <code>words</code>, but pairs are swapped.
   * @return  an <code>ArrayList</code> of words of <code>words</code> with
   *     every two adjacent elements swapped with
   *          each other. If <code>words</code> has an odd number of elements
   * the last element is ignored.
   */
  public ArrayList<String> swapWords() {
    ArrayList<String> swapped = new ArrayList<>();
    for (int i = 1; i < words.size(); i = i + 2) {
      swapped.add(words.get(i));
      swapped.add(words.get(i - 1));
    }
    return swapped;
  }

  /**
   * Method to combine <code>words</code> with the given <code>ArrayList</code>.
   * @param moreWords - an <code>ArrayList</code> to be appended to the end of
   *     <code>words</code>.
   * @return  an <code>ArrayList</code> of the elements of <code>words</code>
   *     appended by the given
   *          <code>ArrayList</code>.
   */
  public ArrayList<String> combineLists(ArrayList<String> moreWords) {
    ArrayList<String> combined =
        new ArrayList<>(words.size() + moreWords.size());
    combined.addAll(words);
    combined.addAll(moreWords);
    return combined;
  }

  /**
   * Method to reverse the order of the elements of <code>words</code>.
   * @return an <code>ArrayList</code> of the elements of <code>words</code> in
   *     reverse order.
   */
  public ArrayList<String> reverse() {
    ArrayList<String> reversed = new ArrayList<>();
    for (int i = words.size() - 1; i >= 0; i--) {
      reversed.add(words.get(i));
    }
    return reversed;
  }

  /**
   * Method to return an <code>ArrayList</code> of the elements of
   * <code>words</code> without duplicates.
   * @return an <code>ArrayList</code> of the elements of <code>words</code>
   *     without duplicates.
   */
  public ArrayList<String> withoutDuplicates() {
    ArrayList<String> withoutDuplicates = new ArrayList<>();
    for (String word : words) {
      if (!withoutDuplicates.contains(word)) {
        withoutDuplicates.add(word);
      }
    }
    return withoutDuplicates;
  }

  /**
   * Method to return an array of integers representing the frequencies of
   * words.
   * @return  an array of integers representing the frequencies of words, each
   *     element representing the frequency
   *          of the word at the same index in the <code>ArrayList</code>
   * returned by <code>withoutDuplicates()</code>
   */
  public int[] wordFrequencies() {
    int[] frequencies = new int[withoutDuplicates().size()];
    ArrayList<String> withoutDuplicates = withoutDuplicates();
    for (int i = 0; i < withoutDuplicates.size(); i++) {
      for (String word : words) {
        if (withoutDuplicates.get(i).equals(word)) {
          frequencies[i]++;
        }
      }
    }
    return frequencies;
  }

  /**
   * Accessor method for <code>words</code>.
   * @return the instance variable <code>words</code>.
   */
  public List<String> getWords() { return words; }

  class StringAlphabetize implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
      return s1.compareToIgnoreCase(s2);
    }
  }
}
