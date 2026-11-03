package org.example;

import java.util.Locale;

public class Park {
    private final String name;

    public Park(String name) {
        this.name = name;
    }

    // Нестатический внутренний класс: аттракцион связан с конкретным парком.
    public class Attraction {
        private final String name;
        private final String workingHours;
        private final double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Парк: " + Park.this.name);
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.printf(Locale.ROOT, "Стоимость: %.2f%n", price);
        }
    }
}
