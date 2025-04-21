package org.example.Exception;//Приложения часто должны обрабатывать и преобразовывать данные, которые поступили от пользователей.
// Важно уметь обрабатывать ошибки при вводе и уметь ограничивать количество попыток, в том числе и с помощью
// написания собственных классов-исключений.
//        Перед вами программа-калькулятор сложных процентов. Допишите код классов-исключений LimitException
//        (для ограничения количества попыток) и InputException (для ошибок при вводе), а также добавьте их обработку.
//        Исключение LimitException должно быть унаследовано от класса RuntimeException.
//        Помимо текста исключения оно должно принимать количество попыток ввода attempts в виде целого числа.
//        Добавьте обработку LimitException в методе main(). При превышении лимита попыток предусмотрите вывод сообщения:
//        Превышен лимит ошибок ввода: n, где n — количество реальных попыток.
//        Исключение InputException должно быть унаследовано от класса Exception.
//        При обработке ошибки предусмотрите вывод для пользователей следующих сообщений:
//
//        Введено отрицательное значение;
//        Введено не число;
//        Ошибка ввода: <подробное сообщение об ошибке>.
//        Сгенерируйте нужные исключения внутри класса FinancialCalculatorException.

import java.util.InputMismatchException;
import java.util.Scanner;

public class FinancialCalculatorException {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            calculate();
        } catch (LimitException e){
            System.out.println("Превышено количество попыток ввода: " + e.getCount());
        }
        // перехват исключения LimitException
    }

    public static double getInterest(final double rate, final int time, final double principal) {
        final double multiplier = Math.pow(1.0 + rate/100.0, time) - 1.0;
        return multiplier * principal;
    }

    public static int getIntLimited(String greeting, int attempts) {
        for (int counter = 0; counter < attempts; counter++) {
            try {
                System.out.println(greeting + " => ");
                try {
                    final int value = Integer.parseInt(scanner.nextLine());
                    if(value<0){
                        throw new InputException("введено отрицательное число");
                    }
                    // проверка на отрицательное значение
                    // сгенерируйте исключение "Введено отрицательное значение"
                    return value;
                } catch (NumberFormatException exception) {
                    throw new InputException("введено не число");
                    // сгенерируйте исключение "Введено не число"
                }
            } catch (InputException exception) {
                System.out.println("Ошибка ввода: "+exception.getMessage());
                // сгенерируйте вывод формата "Ошибка ввода: " + информация об исключении
            }
        }
        throw new LimitException("Превышен лимит ошибок ввода", attempts);
        // сгенерируйте исключение LimitException с сообщением "Превышен лимит ошибок ввода"
    }

    public static double getDoubleLimited(String greeting, int attempts) {
        for (int counter = 0; counter < attempts; counter++) {
            try {
                System.out.println(greeting + " => ");
                // добавьте недостающий код
                try {
                    final double value = Double.parseDouble(scanner.nextLine());
                    if(value<0.0){
                        throw new InputException("введено отрицательное число");
                    }
                    // проверка на отрицательное значение
                    // сгенерируйте исключение "Введено отрицательное значение"
                    return value;
                } catch (NumberFormatException exception) {
                    throw new InputException("введено не число");
                    // сгенерируйте исключение "Введено не число"
                }
            } catch (InputException exception) {
                System.out.println("Ошибка ввода: "+exception.getMessage());
            }
        }
        // сгенерируйте исключение LimitException
        throw new LimitException("Превышен лимит ошибок ввода", attempts);
    }

    public static void calculate() {
        final double rate = getDoubleLimited("Введите ставку", 3);
        final double principal = getDoubleLimited("Введите размер вклада", 3);
        final int time = getIntLimited("Введите срок вклада в месяцах", 5);
        System.out.println("Ваша выгода = " + getInterest(rate, time, principal));
    }
}

