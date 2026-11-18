package org.example.lesson27;

public final class NumberComparator {
    private NumberComparator() {
    }

    /** Возвращает -1, если первое число меньше; 0, если числа равны; 1, если первое больше. */
    public static int compare(int first, int second) {
        return Integer.compare(first, second);
    }
}
