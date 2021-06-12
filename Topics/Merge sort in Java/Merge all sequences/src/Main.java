import java.util.*;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        final int lineCount = Integer.parseInt(scanner.nextLine());
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, lineCount).forEach(
            i -> Arrays.stream(scanner.nextLine().split(" "))
            .skip(1)
            .mapToInt(Integer::parseInt)
            .forEach(list::add)
        );
        Collections.sort(list);
        Collections.reverse(list);
        list.forEach(element -> System.out.print(element + " "));
    }
}