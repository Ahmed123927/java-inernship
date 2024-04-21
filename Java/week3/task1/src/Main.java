public class Main {
    public static void main(String[] args) {


        int[] numbers = {3, 7, 2, 5, 7, 9, 1, 7};
        int target=3;
        int fIndex=-1;

        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i]==target){
                if(i>=5){
                    fIndex=i;
                    break;
                }
            }
        }
        System.out.println(fIndex);
    }
}