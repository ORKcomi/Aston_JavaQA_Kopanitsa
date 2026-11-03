# Aston Java QA — домашнее задание 2.3

Ветка: **Lesson_2.3**. Версия Java: **11**.

Выполнены все три пункта задания: класс товара, массив из пяти товаров и парк с внутренним классом аттракциона.

## Класс товара

[Product.java](src/main/java/org/example/Product.java) содержит шесть полей:

- название;
- дата производства;
- производитель;
- страна происхождения;
- цена;
- состояние бронирования покупателем.

Конструктор получает все шесть значений и записывает их в поля объекта.
Метод `printInfo()` выводит информацию о товаре в консоль, включая статус бронирования «Да» или «Нет».

В [Lesson23.java](src/main/java/org/example/Lesson23.java) создаётся массив `Product[5]`.
Каждая ячейка заполняется отдельным объектом через `new Product(...)`.
Цикл выводит информацию обо всех пяти товарах.

## Парк и внутренний класс

[Park.java](src/main/java/org/example/Park.java) содержит нестатический внутренний класс `Attraction`.
Объект аттракциона хранит название, время работы и стоимость.
Аттракцион создаётся через объект парка: `park.new Attraction(...)`.
Метод `printInfo()` выводит его данные и название связанного с ним парка.

В `Lesson23.main()` создаются парк и три аттракциона, затем печатается информация о каждом из них.
Названия производителей, даты, расписание и цены являются учебными тестовыми данными.

## Запуск

1. Открыть проект в IntelliJ IDEA и выбрать ветку `Lesson_2.3`.
2. В **File → Project Structure → Project** выбрать JDK 11 и Language level 11.
3. Открыть `src/main/java/org/example/Lesson23.java`.
4. Нажать зелёный треугольник рядом с `main()` и выбрать **Run 'Lesson23.main()'**.

Можно также запустить `Main.main()` — он вызывает `Lesson23.main()`.
На компьютере, где выполнялось задание, JDK 11 расположен в `C:\Users\PC-Gamer\.jdks\temurin-11.0.32.1+1`.
Если SDK отсутствует в списке, добавить эту папку через **Add SDK → JDK**.

Запуск через PowerShell из корня проекта:

```powershell
$jdkPath = 'C:\Users\PC-Gamer\.jdks\temurin-11.0.32.1+1'
New-Item -ItemType Directory -Force -Path target/classes | Out-Null
$sourceFiles = Get-ChildItem src/main/java/org/example -Filter *.java | Select-Object -ExpandProperty FullName
& "$jdkPath/bin/javac.exe" -encoding UTF-8 --release 11 -d target/classes $sourceFiles
& "$jdkPath/bin/java.exe" '-Dfile.encoding=UTF-8' -cp target/classes org.example.Lesson23
```

Результат запуска сохранён в [docs/lesson-2.3-output.txt](docs/lesson-2.3-output.txt).

Проверены компиляция и запуск на JDK 11, а также сборка Maven `package`.
Отдельно проверены значения всех полей, оба статуса бронирования и связь аттракционов с разными парками.

## Ссылка для сдачи

[Ветка Lesson_2.3 с решением](https://github.com/ORKcomi/Aston_JavaQA_Kopanitsa/tree/Lesson_2.3)
