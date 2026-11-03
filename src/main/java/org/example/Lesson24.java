package org.example;

import org.example.lesson24.animals.Animal;
import org.example.lesson24.animals.Bowl;
import org.example.lesson24.animals.Cat;
import org.example.lesson24.animals.Dog;
import org.example.lesson24.figures.Circle;
import org.example.lesson24.figures.Rectangle;
import org.example.lesson24.figures.Shape;
import org.example.lesson24.figures.Triangle;

public class Lesson24 {
    public static void main(String[] args) {
        Cat[] cats = {
                new Cat("Барсик", 10),
                new Cat("Мурзик", 15),
                new Cat("Рыжик", 20),
                new Cat("Тимка", 5),
                new Cat("Васька", 30)
        };
        Dog[] dogs = {
                new Dog("Бобик"),
                new Dog("Шарик")
        };

        System.out.println("ЗАДАНИЕ 1. ЖИВОТНЫЕ");
        cats[0].run(200);
        cats[0].run(201);
        cats[0].swim(5);
        dogs[0].run(150);
        dogs[0].run(500);
        dogs[0].run(501);
        dogs[0].swim(10);
        dogs[0].swim(11);

        System.out.println("\nСОЗДАННЫЕ ОБЪЕКТЫ");
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Всего животных: " + Animal.getAnimalCount());

        Bowl bowl = new Bowl(40);
        System.out.println("\nПЕРВОЕ КОРМЛЕНИЕ");
        System.out.println("Еды в миске: " + bowl.getFood() + " г.");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }
        printFeedingStatus(cats, bowl);

        System.out.println("\nПОПОЛНЕНИЕ МИСКИ И ПОВТОРНОЕ КОРМЛЕНИЕ");
        bowl.addFood(50);
        System.out.println("Добавлено 50 г. Еды в миске: " + bowl.getFood() + " г.");
        for (Cat cat : cats) {
            if (!cat.isSatiated()) {
                cat.eat(bowl);
            }
        }
        printFeedingStatus(cats, bowl);

        Shape[] shapes = {
                new Circle(3, "Голубой", "Синий"),
                new Rectangle(4, 6, "Зелёный", "Чёрный"),
                new Triangle(3, 4, 5, "Жёлтый", "Красный")
        };

        System.out.println("\nЗАДАНИЕ 2. ГЕОМЕТРИЧЕСКИЕ ФИГУРЫ");
        for (Shape shape : shapes) {
            System.out.println();
            shape.printInfo();
        }
    }

    private static void printFeedingStatus(Cat[] cats, Bowl bowl) {
        System.out.println("Сытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + ": " + (cat.isSatiated() ? "сыт" : "голоден"));
        }
        System.out.println("Остаток еды: " + bowl.getFood() + " г.");
    }
}
