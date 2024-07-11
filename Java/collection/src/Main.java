public class Main {
    public static void main(String[] args) {

        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("car");
        dictionary.addWord("bus");


        dictionary.printWordsByLetter('c');
        dictionary.printWordsByLetter('b');
        dictionary.printAllWords();    }
}