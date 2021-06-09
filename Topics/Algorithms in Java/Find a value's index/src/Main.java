import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = readArray(scanner.nextLine());
        int value = scanner.nextInt();

        int index = findIndex(numbers, value);
        System.out.println(index);
    }

    static int findIndex(int[] numbers, int value) {
        // implement me
        int index = -1;
//        for (int i = 0; i < numbers.length; i++) {
//            if (numbers[i] == value) {
//                index = i;
//            }
//        }
//        return index;
        return IntStream.iterate(numbers.length - 1, i -> i >= 0, i -> i - 1)
                .filter(i -> numbers[i] == value).findFirst().orElse(-1);
    }

    // utility class for parsing int[], do not change it
    private static int[] readArray(String line) {
        String[] strings = line.trim().split(" ");
        int[] numbers = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }
        return numbers;
    }
}