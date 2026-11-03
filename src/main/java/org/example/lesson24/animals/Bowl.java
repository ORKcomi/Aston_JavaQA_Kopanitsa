package org.example.lesson24.animals;

public class Bowl {
    private int food;

    public Bowl(int food) {
        if (food < 0) {
            throw new IllegalArgumentException("Количество еды не может быть отрицательным.");
        }
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public boolean addFood(int amount) {
        if (amount < 0) {
            System.out.println("Нельзя добавить отрицательное количество еды.");
            return false;
        }
        if (amount > Integer.MAX_VALUE - food) {
            System.out.println("Нельзя добавить столько еды: превышен допустимый объем миски.");
            return false;
        }
        food += amount;
        return true;
    }

    public boolean decreaseFood(int amount) {
        if (amount < 0) {
            System.out.println("Нельзя убрать отрицательное количество еды.");
            return false;
        }
        if (amount > food) {
            return false;
        }
        food -= amount;
        return true;
    }
}
