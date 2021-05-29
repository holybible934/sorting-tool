import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        String subStr = scanner.nextLine();

        System.out.println(inputLine.split(subStr, -1).length - 1);

        Pattern pattern = Pattern.compile(subStr);
        Matcher matcher = pattern.matcher(inputLine);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        System.out.println(counter);
    }
}