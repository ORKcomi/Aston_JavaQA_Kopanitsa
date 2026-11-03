package org.example.lesson24.figures;

import java.util.Locale;

public interface Shape {
    String getName();

    double getArea();

    double getPerimeter();

    String getFillColor();

    String getBorderColor();

    default void printInfo() {
        System.out.printf(Locale.ROOT,
                "Фигура: %s%nПериметр: %.2f%nПлощадь: %.2f%n"
                        + "Цвет заливки: %s%nЦвет границы: %s%n",
                getName(), getPerimeter(), getArea(), getFillColor(), getBorderColor());
    }
}
