import java.util.*;

public class WordDictionary {
    private Map<Character, List<String>> wordsMap;

    public WordDictionary() {
        wordsMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            wordsMap.put(c, new ArrayList<>());
        }
    }

    public void addWord(String word) {
        if (word != null && !word.isEmpty() && Character.isLetter(word.charAt(0))) {
            char initialLetter = Character.toLowerCase(word.charAt(0));
            if (wordsMap.containsKey(initialLetter)) {
                List<String> wordsList = wordsMap.get(initialLetter);
                wordsList.add(word);
                Collections.sort(wordsList);
            }
        }
    }

    public void printWordsByLetter(char letter) {
        letter = Character.toLowerCase(letter);
        if (wordsMap.containsKey(letter)) {
            List<String> wordsList = wordsMap.get(letter);
            System.out.println("Words starting with '" + letter + "': " + String.join(", ", wordsList));
        } else {
            System.out.println("No words found for the letter: " + letter);
        }
    }

    public void printAllWords() {
        for (char c = 'a'; c <= 'z'; c++) {
            printWordsByLetter(c);
        }
    }
}
