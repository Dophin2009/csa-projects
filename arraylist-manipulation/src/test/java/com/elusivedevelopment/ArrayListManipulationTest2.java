package com.elusivedevelopment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListManipulationTest2 {

    private ArrayListManipulation alm, almShort, almShort2, almEmpty;

    @Before
    public void setup() {
        alm = new ArrayListManipulation("speech.txt");
        almShort = new ArrayListManipulation("short.txt");
        almShort2 = new ArrayListManipulation("short2.txt");
        almEmpty = new ArrayListManipulation("empty.txt");
    }

    @Test
    public void numWordsTest() {
        Assert.assertEquals(3081, alm.numWords());
    }

    @Test
    public void longestWordTest() {
        Assert.assertNull(almEmpty.longestWord());
        Assert.assertEquals("responsibilities.", alm.longestWord());
    }

    @Test
    public void indexOfWordTest() {
        Assert.assertEquals(7, alm.indexOfWord("Reichstag"));
        Assert.assertEquals(205, alm.indexOfWord("Socialist"));
        Assert.assertEquals(-1, alm.indexOfWord("Todd"));
    }

    @Test
    public void countWordTest() {
        Assert.assertEquals(21, alm.countWord("German"));
    }

    @Test
    public void startsWithLetterTest() {
        ArrayList<String> wordsStartingWithQ = new ArrayList<>();
        Collections.addAll(wordsStartingWithQ, "quite", "quickly,", "question", "questions.", "questions");
        Assert.assertEquals(wordsStartingWithQ, alm.startsWithLetter('q'));
    }

    @Test
    public void alphabetizeTest() {
        ArrayList<String> alphabetized = new ArrayList<>();
        Collections.addAll(alphabetized, "a", "Bangladesh", "capital", "city", "England", "in", "India",
                "is", "is", "of", "Russia", "the");
        Assert.assertEquals(alphabetized, almShort.alphabetize());
    }

    @Test
    public void maxTest() {
        Assert.assertNull(almEmpty.max());
        Assert.assertEquals("a", almShort.max());
    }

    @Test
    public void minTest() {
        Assert.assertNull(almEmpty.min());
        Assert.assertEquals("the", almShort.min());
    }

    @Test
    public void swapWordsTest() {
        ArrayList<String> swapped = new ArrayList<>();
        Collections.addAll(swapped, "is", "Bangladesh", "city", "a", "India", "in", "is", "England",
                "capital", "the", "Russia", "of");
        Assert.assertEquals(swapped, almShort.swapWords());
    }

    @Test
    public void combineListsTest() {
        ArrayList<String> combined = new ArrayList<>();
        Collections.addAll(combined, "Bangladesh", "is", "a", "city", "in", "India", "England", "is", "the",
                "capital", "of", "Russia", "The", "only", "good", "goblins", "are", "the", "ones", "that", "never",
                "come", "out", "of", "their", "holes.");
        Assert.assertEquals(combined, almShort.combineLists((ArrayList<String>) almShort2.getWords()));
    }

    @Test
    public void reverseTest() {
        ArrayList<String> reversed = new ArrayList<>();
        Collections.addAll(reversed, "holes.", "their", "of", "out", "come", "never", "that", "ones", "the", "are", "goblins", "good", "only", "The");
        Assert.assertEquals(reversed, almShort2.reverse());
    }

    @Test
    public void withoutDuplicatesTest() {
        ArrayList<String> sansDuplicates = new ArrayList<>();
        Collections.addAll(sansDuplicates, "Bangladesh", "is", "a", "city", "in", "India", "England", "the",
                "capital", "of", "Russia");
        Assert.assertEquals(sansDuplicates, almShort.withoutDuplicates());
    }

    @Test
    public void wordFrequenciesTest() {
        int[] frequencies = new int[] { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        Assert.assertArrayEquals(frequencies, almShort.wordFrequencies());
    }

}
