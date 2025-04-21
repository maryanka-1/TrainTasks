package org.example.Interface;

public class Circle implements Figure {
    private final double r;

    public Circle(double r) {
        this.r = r;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.r, 2.0);
    }
}
