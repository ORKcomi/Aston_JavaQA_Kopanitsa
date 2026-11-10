package org.example.lesson25;

/** Ошибка преобразования элемента массива в int; индексы строки и столбца начинаются с нуля. */
public class MyArrayDataException extends Exception {
    private static final long serialVersionUID = 1L;

    private final int row;
    private final int column;
    private final String value;

    public MyArrayDataException(int row, int column, String value, NumberFormatException cause) {
        super("Не удалось преобразовать значение " + (value == null ? "null" : "\"" + value + "\"")
                + " в int: ячейка [" + row + "][" + column + "]"
                + " (строка " + (row + 1) + ", столбец " + (column + 1) + ").", cause);
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
