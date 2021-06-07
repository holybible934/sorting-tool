import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Random rand = new Random(input[1] + input[2]);
        int sum = IntStream.range(0, input[0])
                .map(i -> rand.nextInt(input[2] - input[1] + 1) + input[1])
                .sum();
        System.out.println(sum);
    }
}