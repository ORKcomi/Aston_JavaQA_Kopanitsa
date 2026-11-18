package org.example.lesson27;

/** Четыре арифметических действия с двумя целыми числами. */
public final class Calculator {
    private Calculator() {
    }

    public static long add(int first, int second) {
        return (long) first + second;
    }

    public static long subtract(int first, int second) {
        return (long) first - second;
    }

    public static long multiply(int first, int second) {
        return (long) first * second;
    }

    /** Деление сохраняет дробную часть; деление на ноль запрещено. */
    public static double divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Деление на ноль запрещено.");
        }
        return (double) dividend / divisor;
    }
}
