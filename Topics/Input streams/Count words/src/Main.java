import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String input = reader.readLine();
        if (input.length() > 0) {
            System.out.println(
                    Arrays.stream(input.split("\\s+", 0))
                            .filter(str -> str.length() > 0)
                            .count()
            );
        } else {
            System.out.println(0);
        }
        reader.close();
    }
}