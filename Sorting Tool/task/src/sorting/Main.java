package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length > 0 && Arrays.asList(args).contains("-sortIntegers")) {
            List<Integer> integerList = sortIntegers(scanner);
            System.out.printf("Total numbers: %d.%nSorted data: ", integerList.size());
            integerList.stream().sorted().forEachOrdered(e -> System.out.print(e + " "));
            System.out.println();
        } else if (args.length > 0 && "-dataType".equals(args[0])) {
            String dataType = args[1];
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
    }

    private static List<Integer> sortIntegers(Scanner scanner) {
        List<Integer> integerList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            integerList.add(scanner.nextInt());
        }
        return integerList;
    }

    private static void printLongestWord(Scanner scanner) {
        int totalWords = 0;
        String longestWord = "";
        int counter = 0;
        while (scanner.hasNext()) {
            String inputWord = scanner.next();
            totalWords++;
            if (inputWord.length() > longestWord.length()) {
                longestWord = inputWord;
                counter = 1;
            } else if (inputWord.compareTo(longestWord) == 0) {
                counter++;
            } else if (inputWord.length() == longestWord.length()
                    && inputWord.compareTo(longestWord) > 0) {
                longestWord = inputWord;
                counter = 1;
            }
        }
        System.out.println("Total words: " + totalWords + ".");
        System.out.print("The longest word: " + longestWord);
        System.out.println(" (" + counter + " time(s), " + counter * 100 / totalWords + "%).");
    }

    private static void printLongestLine(Scanner scanner) {
        int totalLines = 0;
        String longestLine = "";
        int counter = 0;
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            totalLines++;
            if (inputLine.length() > longestLine.length()) {
                longestLine = inputLine;
                counter = 1;
            } else if (inputLine.compareTo(longestLine) == 0) {
                counter++;
            } else if (inputLine.length() == longestLine.length()
                    && inputLine.compareTo(longestLine) > 0) {
                longestLine = inputLine;
                counter = 1;
            }
        }
        System.out.println("Total lines: " + totalLines + ".\n" + "The longest line:");
        System.out.println(longestLine);
        System.out.println("(" + counter + " time(s), " + counter * 100 / totalLines + "%).");
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
        System.out.println("Total numbers: " + totalInput + ".");
        System.out.print("The greatest number: " + greatest);
        System.out.println(" (" + counter + " time(s), " + counter * 100 / totalInput + "%).");
    }
}
