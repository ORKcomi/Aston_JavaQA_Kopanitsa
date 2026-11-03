package org.example.lesson24.animals;

public abstract class Animal {
    private static int animalCount;

    private final String name;
    private final int maxRunDistance;
    private final int maxSwimDistance;

    protected Animal(String name, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public boolean run(int distance) {
        if (distance < 0) {
            System.out.println(name + " не может пробежать отрицательное расстояние.");
            return false;
        }
        if (distance > maxRunDistance) {
            System.out.println(name + " не может пробежать " + distance
                    + " м. Максимум: " + maxRunDistance + " м.");
            return false;
        }
        System.out.println(name + " пробежал " + distance + " м.");
        return true;
    }

    public boolean swim(int distance) {
        if (distance < 0) {
            System.out.println(name + " не может проплыть отрицательное расстояние.");
            return false;
        }
        if (distance > maxSwimDistance) {
            System.out.println(name + " не может проплыть " + distance
                    + " м. Максимум: " + maxSwimDistance + " м.");
            return false;
        }
        System.out.println(name + " проплыл " + distance + " м.");
        return true;
    }
}
