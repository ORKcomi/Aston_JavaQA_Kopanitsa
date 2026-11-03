package org.example.lesson24.figures;

public interface Polygon extends Shape {
    double[] getSideLengths();

    // У многоугольников периметр равен сумме длин всех сторон.
    @Override
    default double getPerimeter() {
        double perimeter = 0;
        for (double side : getSideLengths()) {
            perimeter += side;
        }
        return perimeter;
    }
}
