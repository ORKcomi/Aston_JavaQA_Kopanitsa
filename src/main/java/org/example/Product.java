package org.example;

import java.util.Locale;

public class Product {
    private final String name;
    private final String productionDate;
    private final String manufacturer;
    private final String countryOfOrigin;
    private final double price;
    private final boolean reserved;

    public Product(String name, String productionDate, String manufacturer,
                   String countryOfOrigin, double price, boolean reserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.reserved = reserved;
    }

    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.printf(Locale.ROOT, "Цена: %.2f%n", price);
        System.out.println("Забронирован покупателем: " + (reserved ? "Да" : "Нет"));
    }
}
