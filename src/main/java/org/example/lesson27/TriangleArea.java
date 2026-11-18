package org.example.lesson27;

/** Площадь треугольника по трём сторонам, вычисленная по формуле Герона. */
public final class TriangleArea {
    private TriangleArea() {
    }

    public static double calculate(double a, double b, double c) {
        if (!Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)
                || a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными конечными числами.");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны должны удовлетворять неравенству треугольника.");
        }

        double halfPerimeter = (a + b + c) / 2;
        double area = Math.sqrt(halfPerimeter * (halfPerimeter - a)
                * (halfPerimeter - b) * (halfPerimeter - c));
        if (!Double.isFinite(area) || area <= 0) {
            throw new IllegalArgumentException("Размеры не позволяют вычислить положительную площадь в double.");
        }
        return area;
    }
}
