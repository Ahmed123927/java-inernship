import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num1 = 0;
        int num2 = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.print("Enter the first integer: ");
                num1 = scanner.nextInt();
                System.out.print("Enter the second integer: ");
                num2 = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter input again please");

            }
        }
    }
}