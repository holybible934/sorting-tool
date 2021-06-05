import java.util.function.*;

class Operator {

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
            
        return s -> condition.test(s) ? ifTrue.apply(s) : ifFalse.apply(s); // your implementation here

    }
}
