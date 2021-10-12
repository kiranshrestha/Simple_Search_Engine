import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int searchIndexOfLastOccurrence(int[] numbers, int value) {
        // write your code here
        int ind = -1;
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] == value){
                ind = i;
            }
        }

        return ind;
    }

    /* Do not change code below */
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] numbers;
        final int k;
        if (scanner.hasNextInt()) {
            numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            k = Integer.parseInt(scanner.nextLine());
        } else {
            numbers = new int[0];
            k = 10;
        }
        System.out.println(searchIndexOfLastOccurrence(numbers, k));
    }
}