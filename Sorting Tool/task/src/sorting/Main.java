package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dataType = "";
        if ("-dataType".equals(args[0])) {
            dataType = args[1];
        }
        switch (dataType) {
            case "line":
                printLongestLine(scanner);
                break;
            case "long":
                printGreatestLong(scanner);
                break;
            case "word":
            default:
                printLongestWord(scanner);
                break;
        }
    }

    private static void printLongestWord(Scanner scanner) {
    }

    private static void printLongestLine(Scanner scanner) {
    }

    private static void printGreatestLong(Scanner scanner) {
        int totalInput = 0;
        long greatest = Long.MIN_VALUE;
        int counter = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            totalInput++;
            if (number > greatest) {
                greatest = number;
                counter = 1;
            }
            else if (greatest == number) {
                counter++;
            }
        }

        System.out.printf("Total numbers: %d%n", totalInput);
        System.out.printf("The greatest number: %d (%d time(s)%n)", greatest, counter);
    }
}
