package sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public enum SORTING_DATA_TYPE {
        NATURAL_NUMBERS,
        NATURAL_WORDS,
        NATURAL_LINES,
        BYCOUNT_NUMBERS,
        BYCOUNT_WORDS,
        BYCOUNT_LINES
    }

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
                sortingLinesByCount(inputLines);
                break;
            case BYCOUNT_WORDS:
                sortingWordsByCount(inputLines);
                break;
            case BYCOUNT_NUMBERS:
                sortingNumbersByConut(inputLines);
                break;
            case NATURAL_WORDS:
                sortingWordsInNatural(inputLines);
                break;
            case NATURAL_NUMBERS:
                sortingNumbersInNatural(inputLines);
                break;
            case NATURAL_LINES:
                sortLinesInNatural(inputLines);
                break;
            default:
                break;
        }
    }

    private static void sortingNumbersByConut(List<String> inputLines) {
        List<Long> inputLongs = getLongList(inputLines);
        LinkedHashMap<Long, Long> longMap = new LinkedHashMap<>();
        for (long num : inputLongs) {
            longMap.putIfAbsent(num, inputLongs.stream().filter(i -> i == num).count());
        }
        longMap = (LinkedHashMap<Long, Long>) MapUtil.sortByValue(longMap);
        System.out.printf("Total numbers: %d.%n", inputLongs.size());
        for (var set : longMap.entrySet()) {
            System.out.printf("%d: %d time(s), %2d%c%n", set.getKey(), set.getValue(), (set.getValue() * 100) / inputLongs.size(), '%');
        }
    }

    @NotNull
    private static List<Long> getLongList(List<String> inputLines) {
        return inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .mapToLong(Long::parseLong)
                .sorted().boxed().collect(Collectors.toList());
    }

    private static void sortingWordsByCount(List<String> inputLines) {
        List<String> inputWords = getWordList(inputLines);
        LinkedHashMap<String, Long> wordMap = new LinkedHashMap<>();
        for (String word : inputWords) {
            wordMap.putIfAbsent(word, inputWords.stream().filter(e -> e.equals(word)).count());
        }
        wordMap = (LinkedHashMap<String, Long>) MapUtil.sortByValue(wordMap);
        System.out.printf("Total numbers: %d.%n", inputWords.size());
        for (var entry : wordMap.entrySet()) {
            System.out.printf("%s: %d time(s), %2d%c%n", entry.getKey(), entry.getValue(), (entry.getValue() * 100) / inputWords.size(), '%');
        }
    }

    @NotNull
    private static List<String> getWordList(List<String> inputLines) {
        return inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .sorted().collect(Collectors.toList());
    }

    private static void sortingLinesByCount(List<String> inputLines) {
        System.out.printf("Total numbers: %d.%n", inputLines.size());
        inputLines.stream()
                .distinct().sorted()
                .forEachOrdered(System.out::println);
    }

    private static void sortingWordsInNatural(List<String> inputLines) {
        List<String> inputWords = getWordList(inputLines);
        System.out.printf("Total words: %d.%n", inputWords.size());
        System.out.print("Sorted data: ");
        inputWords.forEach(word -> System.out.print(word + " "));
    }

    private static void sortingNumbersInNatural(List<String> inputLines) {
        List<Long> inputLongs = getLongList(inputLines);
        System.out.printf("Total numbers: %d.%n", inputLongs.size());
        System.out.print("Sorted data: ");
        inputLongs.forEach(num -> System.out.print(num + " "));
    }

    private static void sortLinesInNatural(List<String> inputLines) {
        System.out.printf("Total numbers: %d.%n", inputLines.size());
        System.out.println("Sorted data: ");
        inputLines.stream().sorted().forEach(System.out::println);
    }

    public static class MapUtil {
        public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
            list.sort(Map.Entry.comparingByValue());

            Map<K, V> result = new LinkedHashMap<>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }

}
