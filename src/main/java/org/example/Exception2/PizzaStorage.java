package org.example.Exception2;

import java.util.InputMismatchException;
import java.util.Scanner;

//Ваш коллега написал программу для хранилища пиццы, но она не работает.
// Кажется, в коде что-то пропущено — найдите и исправьте это.
class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final PizzaStorage storage = new PizzaStorage(10);
        printActionMenu();
        String action = scanner.nextLine();
        while (!"exit".equals(action)) {
            if ("show".equals(action)) {
                System.out.println("Количество пиццы на складе: " + storage.getPizzaCount());
            } else if ("add".equals(action)) {
                try {
                    add(storage);
                } catch (TooMuchPizzaException e) {
                    System.out.println("Невозможно добавить такое количество пиццы на склад");
                }
            } else if ("take".equals(action)) {
                try {
                    take(storage);
                } catch (NotEnoughPizzaException e) {
                    System.out.println("Недостаточное количество пиццы не складе");
                }
            }
            action = scanner.nextLine();
        }
    }

    public static void add(final PizzaStorage storage) {
        try {
            final int count = getPositiveNumber("Введите количество пиццы для добавления => ");
            storage.addPizza(count);
        } catch (IncorrectInputException exception) {
            System.out.println("Произошла ошибка: " + exception.getMessage());
        }
    }

    public static void take(final PizzaStorage storage) {
        try {
            final int count = getPositiveNumber("Введите количество пиццы для удаления => ");
            storage.takePizza(count);
        } catch (IncorrectInputException exception) {
            System.out.println("Произошла ошибка: " + exception.getMessage());
        }
    }

    public static int getPositiveNumber(final String hint) throws IncorrectInputException {
        System.out.println(hint);
        try {
            final int count = scanner.nextInt();
            if (count <= 0) {
                throw new IncorrectInputException("Число должно быть больше 0");
            }
            return count;
        } catch (InputMismatchException exception) {
            throw new IncorrectInputException("Введено не число");
        }
    }

    public static void printActionMenu() {
        System.out.println("add - добавить пиццу на склад");
        System.out.println("take - взять пиццу со склада");
        System.out.println("show - показать количество на складе");
        System.out.println("exit - выход");
        System.out.print("Введите действие => ");
    }

}

public class PizzaStorage {
    private final int maxVolume;
    private int pizzaCount = 0;

    public PizzaStorage(final int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int addPizza(final int count) {
        if (pizzaCount + count > maxVolume) {
            throw new TooMuchPizzaException();
        }
        pizzaCount += count;
        return pizzaCount;
    }

    public int takePizza(final int count) {
        if (pizzaCount - count < 0) {
            throw new NotEnoughPizzaException();
        }
        pizzaCount -= count;
        return pizzaCount;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }
}

