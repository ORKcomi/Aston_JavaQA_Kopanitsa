package org.example;

import org.example.lesson27.Calculator;
import org.example.lesson27.Factorial;
import org.example.lesson27.NumberComparator;
import org.example.lesson27.TriangleArea;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. ФАКТОРИАЛ");
        System.out.println("0! = " + Factorial.calculate(0));
        System.out.println("5! = " + Factorial.calculate(5));

        System.out.println("\n2. ПЛОЩАДЬ ТРЕУГОЛЬНИКА");
        System.out.printf(Locale.ROOT, "Стороны 3, 4, 5: площадь = %.2f%n",
                TriangleArea.calculate(3, 4, 5));

        System.out.println("\n3. АРИФМЕТИЧЕСКИЕ ДЕЙСТВИЯ");
        System.out.println("7 + 3 = " + Calculator.add(7, 3));
        System.out.println("7 - 3 = " + Calculator.subtract(7, 3));
        System.out.println("7 * 3 = " + Calculator.multiply(7, 3));
        System.out.println("7 / 2 = " + Calculator.divide(7, 2));

        System.out.println("\n4. СРАВНЕНИЕ ЦЕЛЫХ ЧИСЕЛ");
        printComparison(5, 3);
        printComparison(3, 5);
        printComparison(5, 5);
    }

    private static void printComparison(int first, int second) {
        int result = NumberComparator.compare(first, second);
        String relation = result < 0 ? "<" : result > 0 ? ">" : "=";
        System.out.println(first + " " + relation + " " + second);
    }
}
