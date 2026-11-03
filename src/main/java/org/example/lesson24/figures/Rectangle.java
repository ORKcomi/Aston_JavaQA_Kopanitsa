package org.example.lesson24.figures;

public class Rectangle implements Polygon {
    private final double width;
    private final double height;
    private final String fillColor;
    private final String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        if (!Double.isFinite(width) || width <= 0
                || !Double.isFinite(height) || height <= 0) {
            throw new IllegalArgumentException("Ширина и высота должны быть положительными конечными числами");
        }
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public String getName() {
        return "Прямоугольник";
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double[] getSideLengths() {
        return new double[]{width, height, width, height};
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
