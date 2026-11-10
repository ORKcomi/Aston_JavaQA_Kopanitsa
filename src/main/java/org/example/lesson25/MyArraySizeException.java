package org.example.lesson25;

/** Ошибка размера двумерного массива: ожидаются ровно четыре строки по четыре элемента. */
public class MyArraySizeException extends Exception {
    private static final long serialVersionUID = 1L;

    public MyArraySizeException(String message) {
        super(message);
    }
}
