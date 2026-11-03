package org.example.lesson24.figures;

public class Triangle implements Polygon {
    private final double a;
    private final double b;
    private final double c;
    private final String fillColor;
    private final String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        if (!Double.isFinite(a) || a <= 0
                || !Double.isFinite(b) || b <= 0
                || !Double.isFinite(c) || c <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными конечными числами");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Сумма любых двух сторон должна быть больше третьей стороны");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public String getName() {
        return "Треугольник";
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - a)
                * (semiPerimeter - b) * (semiPerimeter - c));
    }

    @Override
    public double[] getSideLengths() {
        return new double[]{a, b, c};
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
