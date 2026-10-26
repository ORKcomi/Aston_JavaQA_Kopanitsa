# Aston Java QA — домашнее задание 2.2

Ветка: **Lesson_2.2**. Версия Java: **11**.

Все 14 методов находятся в [Lesson22.java](src/main/java/org/example/Lesson22.java).
Каждый из них вызывается из `Lesson22.main(String[] args)`, результат выводится в консоль.
Класс `Main` также запускает это решение.

## Запуск в IntelliJ IDEA

1. Открыть папку репозитория `Aston_JavaQA_Kopanitsa` и выбрать ветку `Lesson_2.2`.
2. Открыть **File → Project Structure → Project**. Выбрать **SDK 11** и **Language level 11**.
   Если SDK отсутствует в списке, выбрать **Add SDK → JDK** и указать папку установленного JDK 11.
   На компьютере, где выполнялось задание, это `C:\Users\PC-Gamer\.jdks\temurin-11.0.32.1+1`.
3. Открыть `src/main/java/org/example/Lesson22.java`.
4. Нажать зелёный треугольник рядом с `main()` и выбрать **Run 'Lesson22.main()'**.

Для запуска Maven из IDEA также следует выбрать JDK 11 в настройках **Build Tools → Maven → Runner → JRE**.
Сторонние библиотеки для решения не нужны.

## Запуск через PowerShell

Команды нужно выполнять из корня репозитория. При другом расположении Java заменить значение `$jdkPath`.

```powershell
$jdkPath = 'C:\Users\PC-Gamer\.jdks\temurin-11.0.32.1+1'
New-Item -ItemType Directory -Force -Path target/classes | Out-Null
& "$jdkPath/bin/javac.exe" -encoding UTF-8 --release 11 -d target/classes src/main/java/org/example/Lesson22.java src/main/java/org/example/Main.java
& "$jdkPath/bin/java.exe" '-Dfile.encoding=UTF-8' -cp target/classes org.example.Lesson22
```

## Соответствие заданию

| № | Метод | Что делает |
|---|---|---|
| 1 | `printThreeWords()` | Печатает Orange, Banana, Apple отдельными строками. |
| 2 | `checkSumSign()` | Проверяет знак суммы локальных `a` и `b`; нулевая сумма считается положительной. |
| 3 | `printColor()` | Выбирает цвет по локальной `value`: ≤ 0, 1…100, > 100. |
| 4 | `compareNumbers()` | Сравнивает локальные `a` и `b`; равенство относится к `a >= b`. |
| 5 | `checkSumInRange(int a, int b)` | Возвращает `true` для суммы от 10 до 20 включительно. |
| 6 | `printNumberSign(int number)` | Печатает знак числа; ноль считается положительным по условию. |
| 7 | `isNegative(int number)` | Возвращает `true` для отрицательных чисел и `false` для нуля и положительных. |
| 8 | `printString(String text, int count)` | Печатает строку `count` раз с помощью цикла. |
| 9 | `isLeapYear(int year)` | Проверяет делимость года на 4, 100 и 400. |
| 10 | `invertArray()` | Создаёт массив нулей и единиц, инвертирует его циклом с условием. |
| 11 | `fillArray()` | Создаёт массив длиной 100 и заполняет его числами 1…100 циклом. |
| 12 | `multiplyArray()` | В массиве из задания удваивает только элементы меньше 6. |
| 13 | `fillDiagonals()` | Создаёт квадратный массив 5×5 и заполняет обе диагонали единицами. |
| 14 | `createArray(int len, int initialValue)` | Возвращает массив длиной `len`, заполненный `initialValue`. |

В пунктах 10–13 массивы возвращаются из методов, чтобы `main()` мог показать результат.
`Arrays.toString()` используется только для вывода; заполнение и изменение массивов выполнены циклами.

В пунктах 2–4 условие разрешает выбрать любые начальные значения. Здесь заданы:

- пункт 2: `a = 5`, `b = -8` → `Сумма отрицательная`;
- пункт 3: `value = 100` → `Желтый`;
- пункт 4: `a = 10`, `b = 10` → `a >= b`.

Чтобы вручную проверить другие ветви этих трёх методов, можно изменить их локальные значения и повторно запустить программу.
В `main()` уже показаны границы 10 и 20 для пункта 5, числа −1, 0 и 1 для пунктов 6–7,
а также годы 1900, 2000, 2023 и 2024 для пункта 9.

## Результат запуска

Решение скомпилировано и запущено на Eclipse Temurin 11.0.32.1.
Сборка Maven `package` также выполнена успешно с JDK 11.

Полный фактический вывод программы на Java 11 сохранён в [docs/lesson-2.2-output.txt](docs/lesson-2.2-output.txt).
[массивы](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html).
