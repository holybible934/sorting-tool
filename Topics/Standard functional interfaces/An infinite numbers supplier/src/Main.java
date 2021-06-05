import java.util.function.Supplier;
import java.util.stream.IntStream;

class FunctionUtils {

    public static Supplier<Integer> getInfiniteRange() {
        // write your code here
        return IntStream.iterate(0, i -> i + 1).iterator()::nextInt;
    }

}