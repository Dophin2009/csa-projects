package com.elusivedevelopment;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for ArrayList Project
 * @author Mr. Sharick
 *
 */
public class ArrayListManipulationTest {
  ArrayListManipulation alm;
  ArrayListManipulation empty;
  ArrayList<String> original = new ArrayList<String>();
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() throws Exception {
    alm = new ArrayListManipulation("ArrayList_test.txt");
    empty = new ArrayListManipulation("ArrayList_test.txt");
    empty.getWords().clear();
    original.addAll(alm.getWords());
  }

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  // If this test doesn't pass it's okay as long as words are being
  // printed 10 per line. Sometimes whitespace can be different.
  @Test
  public void testPrintTenWordsPerLine() {

    alm.printTenWordsPerLine();
    assertEquals(
        "This is a test file for the ArrayList project This\n"
            +
            "SENTENCE Experiments with Capitalization OF WORDS this sentence is to\n"
            + "duplicate some of the words already in the test file\n"
            +
            "TEST FILE tesT filE Test File Last word supercalifragilisticexpialidocious ",
        outContent.toString());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testNumWords() {
    assertEquals(empty.getWords().size(), empty.numWords());
    assertEquals(alm.getWords().size(), alm.numWords());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testLongestWord() {
    assertEquals(null, empty.longestWord());
    assertEquals("supercalifragilisticexpialidocious", alm.longestWord());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testIndexOfWord() {
    assertEquals(0, alm.indexOfWord("This"));
    assertEquals(0, alm.indexOfWord("this"));
    assertEquals(-1, empty.indexOfWord("Lucy"));
    assertEquals(-1, alm.indexOfWord("Lucy"));
    assertEquals(13, alm.indexOfWord("capitalization"));
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testCountWord() {
    assertEquals(0, empty.countWord("Lucy"));
    assertEquals(0, alm.countWord("Lucy"));
    assertEquals(3, alm.countWord("this"));
    assertEquals(5, alm.countWord("TEST"));
    assertEquals(5, alm.countWord("test"));
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testMax() {
    assertEquals(null, empty.max());
    assertEquals("a", alm.max().toLowerCase());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testMin() {
    assertEquals(null, empty.min());
    assertEquals("words", alm.min().toLowerCase());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testStartsWithLetter() {
    assertEquals(empty.getWords(), empty.startsWithLetter('z'));
    ArrayList<String> tWords = new ArrayList<String>();
    tWords.add("This");
    tWords.add("test");
    tWords.add("the");
    tWords.add("This");
    tWords.add("this");
    tWords.add("to");
    tWords.add("the");
    tWords.add("the");
    tWords.add("test");
    tWords.add("TEST");
    tWords.add("tesT");
    tWords.add("Test");
    assertEquals(tWords, alm.startsWithLetter('t'));
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testAlphabetize() {
    ArrayList<String> a = new ArrayList<String>();
    a.add("a");
    a.add("already");
    a.add("ArrayList");
    a.add("Capitalization");
    a.add("duplicate");
    a.add("Experiments");
    a.add("file");
    a.add("file");
    a.add("FILE");
    a.add("filE");
    a.add("File");
    a.add("for");
    a.add("in");
    a.add("is");
    a.add("is");
    a.add("Last");
    a.add("OF");
    a.add("of");
    a.add("project");
    a.add("SENTENCE");
    a.add("sentence");
    a.add("some");
    a.add("supercalifragilisticexpialidocious");
    a.add("test");
    a.add("test");
    a.add("TEST");
    a.add("tesT");
    a.add("Test");
    a.add("the");
    a.add("the");
    a.add("the");
    a.add("This");
    a.add("This");
    a.add("this");
    a.add("to");
    a.add("with");
    a.add("word");
    a.add("WORDS");
    a.add("words");
    assertEquals(a, alm.alphabetize());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testSwapWords() {
    assertEquals("[]", empty.swapWords().toString());
    String swapped =
        "[is, This, test, a, for, file, ArrayList, the, This, project, Experiments, SENTENCE, Capitalization, with, WORDS, OF, sentence, this, to, is, some, duplicate, the, of, already, words, the, in, file, test, FILE, TEST, filE, tesT, File, Test, word, Last]";
    assertEquals(swapped, alm.swapWords().toString());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testCombineLists() {
    ArrayList<String> moreWords = new ArrayList<String>();
    moreWords.add("A");
    moreWords.add("few");
    moreWords.add("more");
    moreWords.add("words");
    ArrayList<String> wordsCopy = new ArrayList<String>();
    for (String word : alm.getWords())
      wordsCopy.add(word);
    wordsCopy.addAll(moreWords);
    assertEquals(wordsCopy, alm.combineLists(moreWords));
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }

  @Test
  public void testReverse() {
    assertEquals(empty.getWords(), empty.reverse());
    ArrayList<String> wordsCopy = new ArrayList<String>();
    for (String word : alm.getWords())
      wordsCopy.add(0, word);
    assertEquals(wordsCopy, alm.reverse());
    assertEquals(alm.getWords(), original); // test that the list is not changed
  }
}
