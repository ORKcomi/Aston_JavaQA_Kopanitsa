# Домашнее задание 2.5 — массивы и исключения

В ветке находится только решение домашнего задания 2.5.

- `src/main/java/org/example/Lesson25.java` — метод `sumArray(String[][])` и демонстрация.
- `src/main/java/org/example/lesson25/` — проверяемые исключения `MyArraySizeException` и `MyArrayDataException`.
- `src/main/java/org/example/Main.java` — запуск демонстрации `Lesson25`.
- `docs/lesson-2.5-output.txt` — фактический вывод программы.

Метод принимает строковый массив 4×4, проверяет его размер, преобразует элементы в `int` и возвращает сумму типа `long`. При неверном размере выбрасывается `MyArraySizeException`. При ошибке преобразования — `MyArrayDataException` с индексами строки и столбца, исходным значением и причиной `NumberFormatException`.

В `main()` показаны корректный массив с суммой 136, неверное количество строк, неверная длина строки, нечисловое значение и реальное обращение за границы массива. Исключения перехватываются, после чего программа продолжает работу.

Для запуска нужны JDK 11 и Maven:

```sh
mvn compile
java -cp target/classes org.example.Lesson25
```

Запуск `org.example.Main` выводит тот же результат.
