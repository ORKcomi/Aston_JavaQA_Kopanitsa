package org.example;

import org.example.lesson25.MyArrayDataException;
import org.example.lesson25.MyArraySizeException;

public class Lesson25 {
    private static final int ARRAY_SIZE = 4;

    /**
     * Преобразует все элементы массива 4x4 в int и возвращает их сумму.
     * Сумма имеет тип long, чтобы любые шестнадцать значений int сложились без переполнения.
     */
    public static long sumArray(String[][] array)
            throws MyArraySizeException, MyArrayDataException {
        if (array == null) {
            throw new MyArraySizeException("Ожидается массив 4x4, получен null.");
        }
        if (array.length != ARRAY_SIZE) {
            throw new MyArraySizeException("Ожидается массив 4x4, количество строк: " + array.length + ".");
        }

        // Сначала проверяем все строки: неверный размер всегда даёт MyArraySizeException.
        for (int row = 0; row < ARRAY_SIZE; row++) {
            if (array[row] == null) {
                throw new MyArraySizeException("Ожидается массив 4x4, строка [" + row + "] равна null.");
            }
            if (array[row].length != ARRAY_SIZE) {
                throw new MyArraySizeException("Ожидается массив 4x4, длина строки [" + row + "]: "
                        + array[row].length + ".");
            }
        }

        long sum = 0;
        for (int row = 0; row < ARRAY_SIZE; row++) {
            for (int column = 0; column < ARRAY_SIZE; column++) {
                try {
                    sum += Integer.parseInt(array[row][column]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(row, column, array[row][column], e);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] validArray = createValidArray();
        String[][] wrongRowCount = new String[3][4];
        String[][] wrongColumnCount = createValidArray();
        wrongColumnCount[2] = new String[3];
        String[][] invalidData = createValidArray();
        invalidData[1][2] = "ошибка";

        // Каждый элемент examples — отдельный двумерный массив для проверки.
        String[][][] examples = {validArray, wrongRowCount, wrongColumnCount, invalidData};
        String[] titles = {
                "1. Корректный массив 4x4",
                "2. Неверное количество строк (3 вместо 4)",
                "3. Неверная длина строки (3 вместо 4)",
                "4. Нечисловое значение в ячейке [1][2]"
        };

        for (int index = 0; index < examples.length; index++) {
            System.out.println(titles[index]);
            try {
                long sum = sumArray(examples[index]);
                System.out.println("Сумма элементов: " + sum);
            } catch (MyArraySizeException e) {
                System.out.println("Перехвачено MyArraySizeException: " + e.getMessage());
            } catch (MyArrayDataException e) {
                System.out.println("Перехвачено MyArrayDataException: " + e.getMessage());
            }
            System.out.println();
        }

        System.out.println("5. Выход за границы массива");
        try {
            int[] numbers = {10, 20, 30};
            // Последний допустимый индекс — 2; обращение по индексу 3 порождает исключение.
            System.out.println(numbers[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Перехвачено ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        System.out.println("Программа продолжила работу после обработки исключений.");
    }

    private static String[][] createValidArray() {
        return new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
    }
}
