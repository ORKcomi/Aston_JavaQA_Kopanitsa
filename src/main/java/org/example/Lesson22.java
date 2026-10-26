package org.example;

import java.util.Arrays;

public class Lesson22 {

    public static void main(String[] args) {
        System.out.println("Задание 1");
        printThreeWords();

        System.out.println("\nЗадание 2");
        checkSumSign();

        System.out.println("\nЗадание 3");
        printColor();

        System.out.println("\nЗадание 4");
        compareNumbers();

        System.out.println("\nЗадание 5");
        System.out.println("4 + 5: " + checkSumInRange(4, 5));
        System.out.println("5 + 5: " + checkSumInRange(5, 5));
        System.out.println("10 + 10: " + checkSumInRange(10, 10));
        System.out.println("10 + 11: " + checkSumInRange(10, 11));

        System.out.println("\nЗадание 6");
        printNumberSign(-1);
        printNumberSign(0);
        printNumberSign(1);

        System.out.println("\nЗадание 7");
        System.out.println("-1: " + isNegative(-1));
        System.out.println("0: " + isNegative(0));
        System.out.println("1: " + isNegative(1));

        System.out.println("\nЗадание 8");
        printString("Привет, Aston!", 3);

        System.out.println("\nЗадание 9");
        int[] years = {1900, 2000, 2023, 2024};
        for (int year : years) {
            System.out.println(year + ": " + isLeapYear(year));
        }

        System.out.println("\nЗадание 10");
        System.out.println(Arrays.toString(invertArray()));

        System.out.println("\nЗадание 11");
        System.out.println(Arrays.toString(fillArray()));

        System.out.println("\nЗадание 12");
        System.out.println(Arrays.toString(multiplyArray()));

        System.out.println("\nЗадание 13");
        int[][] square = fillDiagonals();
        for (int[] row : square) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nЗадание 14");
        System.out.println(Arrays.toString(createArray(5, 7)));
    }

    // 1. Три слова в столбец.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Знак суммы двух локальных переменных.
    public static void checkSumSign() {
        int a = 5;
        int b = -8;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Цвет зависит от значения локальной переменной value.
    public static void printColor() {
        int value = 100;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Сравнение двух локальных переменных, включая равенство.
    public static void compareNumbers() {
        int a = 10;
        int b = 10;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Сумма должна находиться в диапазоне от 10 до 20 включительно.
    public static boolean checkSumInRange(int a, int b) {
        // long сохраняет правильную сумму даже для больших значений int.
        long sum = (long) a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. По условию ноль относится к положительным числам.
    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println(number + ": Положительное число");
        } else {
            System.out.println(number + ": Отрицательное число");
        }
    }

    // 7. true возвращается только для отрицательного числа.
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Печать строки заданное количество раз.
    public static void printString(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    // 9. Каждый 100-й год не високосный, кроме каждого 400-го.
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    // 10. Замена нулей и единиц с помощью цикла и условия.
    public static int[] invertArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    // 11. Заполнение массива числами от 1 до 100.
    public static int[] fillArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12. Удвоение только тех элементов, которые меньше 6.
    public static int[] multiplyArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        return array;
    }

    // 13. Единицы на обеих диагоналях квадратного массива.
    public static int[][] fillDiagonals() {
        int size = 5;
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            array[i][i] = 1;
            array[i][size - 1 - i] = 1;
        }
        return array;
    }

    // 14. Массив заданной длины с одинаковыми значениями.
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        return array;
    }
}
