import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // put your code here
//        final Scanner scanner = new Scanner(System.in);
//        final int dateTimeCount = scanner.nextInt();
//        scanner.nextLine();
//        List<LocalDateTime> dateTimeList = new ArrayList<>();
//        for (int i = 0; i < dateTimeCount; i++) {
//            dateTimeList.add(LocalDateTime.parse(scanner.nextLine()));
//        }
//        LocalDateTime maxLocalDateTime = dateTimeList.get(0);
//        for (LocalDateTime ldt : dateTimeList) {
//            maxLocalDateTime = maxLocalDateTime.isAfter(ldt) ? maxLocalDateTime : ldt;
//        }
//        System.out.println(maxLocalDateTime);

        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();

        Stream.generate(scanner::next)
                .map(LocalDateTime::parse)
                .limit(limit)
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }
}