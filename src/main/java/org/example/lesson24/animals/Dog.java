package org.example.lesson24.animals;

public class Dog extends Animal {
    private static int dogCount;

    public Dog(String name) {
        super(name, 500, 10);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }
}
