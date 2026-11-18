package org.example.lesson27;

/** Вычисление факториала в пределах типа long. */
public final class Factorial {
    private Factorial() {
    }

    /** Возвращает n! для целого n от 0 до 20 включительно. */
    public static long calculate(int number) {
        if (number < 0 || number > 20) {
            throw new IllegalArgumentException("Факториал типа long определён для чисел от 0 до 20.");
        }
        long result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }
}
