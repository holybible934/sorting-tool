package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        List<String> inputLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            inputLines.add(scanner.nextLine());
        }
        switch (sortingDataType) {
            case BYCOUNT_LINES:
                break;
            case BYCOUNT_WORDS:
                break;
            case BYCOUNT_NUMBERS:
                break;
            case NATURAL_WORDS:
                sortingWordsInNatural(inputLines);
                break;
            case NATURAL_NUMBERS:
                sortingNumbersInNatural(inputLines);
                break;
            case NATURAL_LINES:
                sortLinesInNatural(inputLines);
            default:
                break;
        }
    }

    private static void sortingWordsInNatural(List<String> inputLines) {
        List<String> inputWords = inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .sorted().collect(Collectors.toList());
        System.out.printf("Total words: %d.%n", inputWords.size());
        System.out.print("Sorted data: ");
        inputWords.forEach(word -> System.out.print(word + " "));
    }

    private static void sortingNumbersInNatural(List<String> inputLines) {
        List<Long> inputLongs = inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .mapToLong(Long::parseLong)
                .sorted().boxed().collect(Collectors.toList());
        System.out.printf("Total numbers: %d.%n", inputLongs.size());
        System.out.print("Sorted data: ");
        inputLongs.forEach(num -> System.out.print(num + " "));
    }

    private static void sortLinesInNatural(List<String> inputLines) {
        System.out.printf("Total numbers: %d.%n", inputLines.size());
        System.out.println("Sorted data: ");
        inputLines.stream().sorted().forEach(System.out::println);
    }
}
