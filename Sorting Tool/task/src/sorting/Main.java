package sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public enum SORTING_DATA_TYPE {
        NATURAL_NUMBERS("long", true),
        NATURAL_WORDS("word", true),
        NATURAL_LINES("line", true),
        BYCOUNT_NUMBERS("long", false),
        BYCOUNT_WORDS("word", false),
        BYCOUNT_LINES("line", false);

        private final String mode;
        private final boolean sorted;
        SORTING_DATA_TYPE(String mode, boolean sorted) {
            this.mode = mode;
            this.sorted = sorted;
        }

        public String getMode() {
            return mode;
        }

        public boolean isSorted() {
            return sorted;
        }
    }

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        SORTING_DATA_TYPE sortingDataType = parseArguments(args);
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

    @NotNull
    private static SORTING_DATA_TYPE parseArguments(String[] args) {
        boolean sorted = true;
        String mode = "word";

        for (int index = 0; index < args.length; index++) {
            switch (args[index]) {
                case "-sortingType":
                    switch (index + 1 < args.length ? args[++index] : "") {
                        case "byCount":
                            sorted = false;
                            break;
                        case "natural":
                            sorted = true;
                            break;
                        case "-dataType":   // handle case where dataType follows sortingType without
                            index--;        // specifying a valid option
                        default:
                            System.out.println("No sorting type defined!");
                            break;
                    }
                    break;
                case "-dataType":
                    switch (index + 1 < args.length ? args[++index] : "") {
                        case "long":
                        case "line":
                        case "word":
                            mode = args[index];
                            break;
                        case "-sortingType":    // handle case where sortingType follows dataType without
                            index--;            // specifying a valid option
                        default:
                            System.out.println("No data type defined!");
                            break;
                    }
                    break;
                default:
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n",
                            args[index]);
                    break;
            }
        }
        switch (mode) {
            case "long":
                if (sorted) {
                    return SORTING_DATA_TYPE.NATURAL_NUMBERS;
                } else {
                    return SORTING_DATA_TYPE.BYCOUNT_NUMBERS;
                }
            case "line":
                if (sorted) {
                    return SORTING_DATA_TYPE.NATURAL_LINES;
                } else {
                    return SORTING_DATA_TYPE.BYCOUNT_LINES;
                }
            case "word":
            default:
                if (sorted) {
                    return SORTING_DATA_TYPE.NATURAL_WORDS;
                } else {
                    return SORTING_DATA_TYPE.BYCOUNT_WORDS;
                }
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
