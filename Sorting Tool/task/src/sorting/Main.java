package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalInput = 0;
        long greatest = Long.MIN_VALUE;
        int counter = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            // write your code here
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
