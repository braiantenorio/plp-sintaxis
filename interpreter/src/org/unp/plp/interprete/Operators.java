package org.unp.plp.interprete;
import java.util.function.BinaryOperator;
import java.util.function.BiPredicate;

public class Operators {
    public static final BinaryOperator<Integer> ADD = (a, b) -> a + b;
    public static final BinaryOperator<Integer> SUBTRACT = (a, b) -> a - b;
    public static final BinaryOperator<Integer> MULTIPLY = (a, b) -> a * b;
    public static final BinaryOperator<Integer> DIVIDE = (a, b) -> a / b;

    public static final BiPredicate<Integer, Integer> EQUALS = (a, b) -> a.equals(b);
    public static final BiPredicate<Integer, Integer> LESS_THAN = (a, b) -> a < b;
    public static final BiPredicate<Integer, Integer> GREATER_THAN = (a, b) -> a > b;
    public static final BiPredicate<Integer, Integer> GREATER_THAN_OR_EQUALS = (a, b) -> a >= b;
    public static final BiPredicate<Integer, Integer> LESS_THAN_OR_EQUALS = (a, b) -> a <= b;
}
