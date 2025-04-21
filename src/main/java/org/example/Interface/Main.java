package org.example.Interface;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        System.out.println("Площадь прямоугольника 4см*6см = " + rectangle.getArea());
        Square square = new Square(3.0);
        System.out.println("Площадь квадрата 3см*3см = " + square.getArea());
        Rhombus rhombus = new Rhombus(4.0, 3.0);
        System.out.println("Площадь ромба со стороной 4см и высотой 3см = " + rhombus.getArea());
        Circle circle = new Circle(2.0);
        System.out.println("Площадь круга с радиусом 2см = " + circle.getArea());
    }
}
