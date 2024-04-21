public class Main {
    public static void main(String[] args) {
        
       int [] numbers={1,2,3};
        System.out.println(average(numbers));
    }
    
    public static float average(int []arr){
        int sum=0;

        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }

        return (float) sum /arr.length;

    }
}