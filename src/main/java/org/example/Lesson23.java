package org.example;

public class Lesson23 {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Смартфон", "01.02.2025",
                "TechMobile", "Китай", 1299.90, true);
        productsArray[1] = new Product("Ноутбук", "15.03.2025",
                "ComputerFactory", "Тайвань", 2499.00, false);
        productsArray[2] = new Product("Наушники", "20.04.2025",
                "SoundLab", "Китай", 149.50, true);
        productsArray[3] = new Product("Кофемашина", "10.05.2025",
                "CoffeeHome", "Италия", 899.00, false);
        productsArray[4] = new Product("Книга", "25.06.2025",
                "Учебное издательство", "Беларусь", 39.90, false);

        System.out.println("ТОВАРЫ");
        for (Product product : productsArray) {
            product.printInfo();
            System.out.println();
        }

        Park park = new Park("Парк отдыха");
        Park.Attraction[] attractions = {
                park.new Attraction("Колесо обозрения", "10:00-20:00", 12.50),
                park.new Attraction("Карусель", "10:00-19:00", 5.00),
                park.new Attraction("Автодром", "11:00-21:00", 8.00)
        };

        System.out.println("АТТРАКЦИОНЫ");
        for (Park.Attraction attraction : attractions) {
            System.out.println();
            attraction.printInfo();
        }
    }
}
