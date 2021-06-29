package sorting;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    }

    static class Files {
        Scanner scanner;
        PrintWriter outputFile;
        Files() {
            scanner = new Scanner(System.in);
            outputFile = new PrintWriter(System.out);
        }
    }

    public static void main(final String[] args) {
        Files myFiles = new Files();
        SORTING_DATA_TYPE sortingDataType = parseArguments(args, myFiles);
        List<String> inputLines = new ArrayList<>();
        while (myFiles.scanner.hasNextLine()) {
            inputLines.add(myFiles.scanner.nextLine());
        }
        switch (sortingDataType) {
            case BYCOUNT_LINES:
                sortingLinesByCount(inputLines, myFiles.outputFile);
                break;
            case BYCOUNT_WORDS:
                sortingWordsByCount(inputLines, myFiles.outputFile);
                break;
            case BYCOUNT_NUMBERS:
                sortingNumbersByCount(inputLines, myFiles.outputFile);
                break;
            case NATURAL_WORDS:
                sortingWordsInNatural(inputLines, myFiles.outputFile);
                break;
            case NATURAL_NUMBERS:
                sortingNumbersInNatural(inputLines, myFiles.outputFile);
                break;
            case NATURAL_LINES:
                sortLinesInNatural(inputLines, myFiles.outputFile);
                break;
            default:
                break;
        }
    }

    @NotNull
    private static SORTING_DATA_TYPE parseArguments(String[] args, Files myFiles) {
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
                case "-inputFile":
                    try {
                        if (index + 1 < args.length) {
                            myFiles.scanner = new Scanner(new File(args[++index]));
                        }
                    } catch (FileNotFoundException exception) {
                        System.out.println("No such file! Use standard input instead.");
                    }
                    break;
                case "-outputFile":
                    try {
                        if (index + 1 < args.length) {
                            myFiles.outputFile = new PrintWriter(new File(args[++index]));
                        }
                    } catch (FileNotFoundException exception) {
                        System.out.println("No such file! Use standard output instead.");
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

    private static void sortingNumbersByCount(List<String> inputLines, PrintWriter outputFile) {
        List<Long> inputLongs = getLongList(inputLines);
        LinkedHashMap<Long, Long> longMap = new LinkedHashMap<>();
        for (long num : inputLongs) {
            longMap.putIfAbsent(num, inputLongs.stream().filter(i -> i == num).count());
        }
        longMap = (LinkedHashMap<Long, Long>) MapUtil.sortByValue(longMap);
        outputFile.printf("Total numbers: %d.%n", inputLongs.size());
        for (var set : longMap.entrySet()) {
            outputFile.printf("%d: %d time(s), %2d%c%n", set.getKey(), set.getValue(), (set.getValue() * 100) / inputLongs.size(), '%');
        }
    }

    @NotNull
    private static List<Long> getLongList(List<String> inputLines) {
        return inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .mapToLong(Long::parseLong)
                .sorted().boxed().collect(Collectors.toList());
    }

    private static void sortingWordsByCount(List<String> inputLines, PrintWriter outputFile) {
        List<String> inputWords = getWordList(inputLines);
        LinkedHashMap<String, Long> wordMap = new LinkedHashMap<>();
        for (String word : inputWords) {
            wordMap.putIfAbsent(word, inputWords.stream().filter(e -> e.equals(word)).count());
        }
        wordMap = (LinkedHashMap<String, Long>) MapUtil.sortByValue(wordMap);
        outputFile.printf("Total numbers: %d１.%n", inputWords.size());
        for (var entry : wordMap.entrySet()) {
            outputFile.printf("%s: %d time(s), %2d%c%n", entry.getKey(), entry.getValue(), (entry.getValue() * 100) / inputWords.size(), '%');
        }
    }

    @NotNull
    private static List<String> getWordList(List<String> inputLines) {
        return inputLines.stream().map(line -> line.split("\\s+", 0))
                .flatMap(Arrays::stream)
                .sorted().collect(Collectors.toList());
    }

    private static void sortingLinesByCount(List<String> inputLines, PrintWriter outputFile) {
        outputFile.printf("Total numbers: %d.%n", inputLines.size());
        inputLines.stream()
                .distinct().sorted()
                .forEachOrdered(outputFile::println);
    }

    private static void sortingWordsInNatural(List<String> inputLines, PrintWriter outputFile) {
        List<String> inputWords = getWordList(inputLines);
        outputFile.printf("Total words: %d.%n", inputWords.size());
        outputFile.print("Sorted data: ");
        inputWords.forEach(word -> outputFile.print(word + " "));
    }

    private static void sortingNumbersInNatural(List<String> inputLines, PrintWriter outputFile) {
        List<Long> inputLongs = getLongList(inputLines);
        outputFile.printf("Total numbers: %d.%n", inputLongs.size());
        outputFile.print("Sorted data: ");
        inputLongs.forEach(num -> outputFile.print(num + " "));
    }

    private static void sortLinesInNatural(List<String> inputLines, PrintWriter outputFile) {
        outputFile.printf("Total numbers: %d.%n", inputLines.size());
        outputFile.println("Sorted data: ");
        inputLines.stream().sorted().forEach(outputFile::println);
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
