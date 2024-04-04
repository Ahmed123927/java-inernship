import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Age");
        int age=scanner.nextInt();
        System.out.println("Enter Height");
        double height=scanner.nextDouble();
        System.out.println("is raining?");
        boolean isRaining= scanner.nextBoolean();
        System.out.println("Enter your city name");
        String nameOfCity= scanner.next();


    }
}