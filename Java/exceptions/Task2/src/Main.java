import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "D:/Data/Training/Java/exceptions/Lincoln.txt";
        int wordCounter=0;
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNext()) {
                String word = fileScanner.next().replaceAll("[^a-zA-Z0-9]", "");
                if (!word.isEmpty()) {
//                    System.out.println(word);
                    wordCounter++;
                };
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        }



        System.out.println("words in the file: " + wordCounter);
    }
}