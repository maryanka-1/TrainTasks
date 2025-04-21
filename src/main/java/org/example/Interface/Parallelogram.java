package org.example.Interface;

public abstract class Parallelogram implements Figure {
    double a;
    double h;

    public Parallelogram(double a, double h) {
        this.a = a;
        this.h = h;
    }

    public double getArea() {
        return this.a * this.h;
    }
}
