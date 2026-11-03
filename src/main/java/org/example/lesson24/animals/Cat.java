package org.example.lesson24.animals;

public class Cat extends Animal {
    private static int catCount;

    private final int appetite;
    private boolean satiated;

    public Cat(String name, int appetite) {
        super(validateAppetite(name, appetite), 200, 0);
        this.appetite = appetite;
        catCount++;
    }

    private static String validateAppetite(String name, int appetite) {
        // Проверяем до вызова Animal, чтобы при ошибке не увеличивать счетчик.
        if (appetite < 0) {
            throw new IllegalArgumentException("Аппетит не может быть отрицательным.");
        }
        return name;
    }

    public static int getCatCount() {
        return catCount;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiated() {
        return satiated;
    }

    @Override
    public boolean swim(int distance) {
        System.out.println(getName() + " не умеет плавать.");
        return false;
    }

    public boolean eat(Bowl bowl) {
        if (satiated) {
            System.out.println(getName() + " уже сыт.");
            return false;
        }
        if (bowl == null) {
            System.out.println(getName() + " не может поесть: миска не указана.");
            return false;
        }
        if (!bowl.decreaseFood(appetite)) {
            System.out.println(getName() + " не поел: в миске недостаточно еды. Нужно: "
                    + appetite + " г.");
            return false;
        }
        satiated = true;
        System.out.println(getName() + " съел " + appetite + " г еды и теперь сыт.");
        return true;
    }
}
