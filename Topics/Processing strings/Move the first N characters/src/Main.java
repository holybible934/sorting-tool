import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int startIdx = Integer.parseInt(input[1]);
        if (startIdx > 0 && input[0].length() > startIdx) {
            System.out.println(input[0].substring(startIdx)
                    + input[0].substring(0, startIdx));
        } else {
            System.out.println(input[0]);
        }
    }
}