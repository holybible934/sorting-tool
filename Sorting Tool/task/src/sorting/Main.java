package sorting;

import java.util.*;

public class Main {
    public enum SORTING_DATA_TYPE {
        NATURAL_NUMBERS,
        NATURAL_WORDS,
        NATURAL_LINES,
        BYCOUNT_NUMBERS,
        BYCOUNT_WORDS,
        BYCOUNT_LINES
    };

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> argumentsList = Arrays.asList(args);
        SORTING_DATA_TYPE sortingDataType;
        if (argumentsList.contains("byCount")) {
            if (argumentsList.contains("long")) {
                sortingDataType = SORTING_DATA_TYPE.BYCOUNT_NUMBERS;
            } else if (argumentsList.contains("word")) {
                sortingDataType = SORTING_DATA_TYPE.BYCOUNT_WORDS;
            } else {
                sortingDataType = SORTING_DATA_TYPE.BYCOUNT_LINES;
            }
        } else {
            if (argumentsList.contains("long")) {
                sortingDataType = SORTING_DATA_TYPE.NATURAL_NUMBERS;
            } else if (argumentsList.contains("word")) {
                sortingDataType = SORTING_DATA_TYPE.NATURAL_WORDS;
            } else {
                sortingDataType = SORTING_DATA_TYPE.NATURAL_LINES;
            }
        }
        switch (sortingDataType) {
            case BYCOUNT_LINES:
                break;
            case BYCOUNT_WORDS:
                break;
            case BYCOUNT_NUMBERS:
                break;
            case NATURAL_WORDS:
                break;
            case NATURAL_NUMBERS:
                break;
            case NATURAL_LINES:
            default:
                break;
        }
    }

    private static List<Integer> saveIntegerList(Scanner scanner) {
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
