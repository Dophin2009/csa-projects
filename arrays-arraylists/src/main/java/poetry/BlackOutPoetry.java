// package poetry;
//
// import edu.stanford.nlp.tagger.maxent.MaxentTagger;
//
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
//
// public class BlackOutPoetry {
//
//    private List<String> words;
//
//    public BlackOutPoetry(String filename) {
//        words = new ArrayList<String>();
//
//        try {
//            Scanner reader = new Scanner(new File(filename));
//            while (reader.hasNext()) {
//                words.add(reader.next());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String generateRandomPoem() {
//
//        for (int i = 0; i < words.size() / 150; i++) {
//            words.remove((int) Math.random() * words.size());
//        }
//
//        StringBuilder s = new StringBuilder();
//        for (Object w : words) {
//            s.append(w).append(" ");
//        }
//        return s.toString();
//    }
//
//    public String generatePoem() {
//        MaxentTagger t = new
//        MaxentTagger("models/english-left3words-distsim.tagger"); List<String>
//        nouns = new ArrayList<String>(); nouns.add("NN"); nouns.add("NNS");
//        nouns.add("NNP");
//        nouns.add("NNPS");
//        List<String> verbs = new ArrayList<String>();
//        verbs.add("VB");
//        verbs.add("VBG");
//        verbs.add("VBN");
//        List<String> adverbs = new ArrayList<String>();
//        adverbs.add("RB");
//        adverbs.add("RBR");
//        adverbs.add("RBS");
//        List<String> adjectives = new ArrayList<String>();
//        adjectives.add("JJ");
//        adjectives.add("JJR");
//        adjectives.add("JJS");
//
//        int wordCount = 0;
//        StringBuilder poem = new StringBuilder();
//        while (wordCount < 20) {
//            String randomWord = words.get((int) Math.random() * words.size());
//            String taggedWord = t.tagString(randomWord);
//            String tag = parseTag(taggedWord);
//
//            if (((wordCount % 5 == 0 || wordCount % 5 == 4) &&
//            (nouns.contains(tag)))
//                    || (wordCount % 5 == 1 && adverbs.contains(tag))
//                    || (wordCount % 5 == 2 && verbs.contains(tag))
//                    || (wordCount % 5 == 3 && adjectives.contains(tag))) {
//                poem.append(randomWord).append(" ");
//                wordCount++;
//            }
//        }
//        return String.valueOf(poem);
//    }
//
//    public String parseTag(String wordWithTag) {
//        for (int i = 0; i < wordWithTag.length(); i++) {
//            if (wordWithTag.charAt(i) == '_') {
//                return wordWithTag.substring(i + 1, wordWithTag.length() - 1);
//            }
//        }
//        return "";
//    }
//
//    public List<String> getWords() {
//        return words;
//    }
//
//}
//
//
