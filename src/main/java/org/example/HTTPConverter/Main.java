package org.example.HTTPConverter;

//Доработайте класс Converter.
//Добавьте скрытое неизменяемое поле client с типом данных HttpClient. Поле должно быть
// проинициализировано в публичном конструкторе по умолчанию.
//Добавьте метод getRate, который является приватным;
//в качестве параметра принимает символ валюты (например, JPY для японской иены),
// в которую необходимо конвертировать сумму в рублях;
//возвращает котировку рубля к переданной валюте.
//Реализация метода getRate должна запрашивать котировку рубля к валюте,
// символ которой был передан в параметре. Для получения котировок воспользуйтесь нашим сервисом,
// чтобы отправить курсы валют. Они не актуальные, но вы можете использовать
// их в ознакомительных целях. Отправьте GET-запрос на https://functions.yandexcloud.net/d4ed1i6t3f80hf0p7mer
// и укажите в качестве строки параметров рубль как базовую валюту (параметр base) и переданный символ в качестве
// котируемой валюты (параметр symbols).
//Уберите фиксированные курсы валют. Вместо них при каждом вызове метода convert(double rubles, int currency)
// запрашивайте актуальный курс рубля к заданной валюте при помощи метода getRate.
//Для получения курса рубля к долларам используйте символ USD, для евро — EUR, для иен — JPY.
//Исправьте вычисление суммы при конвертации. Учитывайте, что раньше хранился курс других валют к рублю,а сейчас — наоборот.
//Доработайте класс FinanceApplication — исправьте создание объекта класса Converter.

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Converter {
    private final HttpClient client;
//    double rateUSD;
//    double rateEUR;
//    double rateJPY;

    public Converter() {
        client = HttpClient.newHttpClient();
    }

    private double getRate(String nameCurrency) {
        double result = 0;
        URI url = URI.create("https://functions.yandexcloud.net/d4ed1i6t3f80hf0p7mer?base=RUB&symbols=" + nameCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonElement element = JsonParser.parseString(response.body());
                if (!element.isJsonObject()) {
                    System.out.println("Получен не корректный ответ");
                    return result;
                }
                JsonObject object = element.getAsJsonObject();

                result = object.getAsJsonObject("rates").get(nameCurrency).getAsDouble();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Проблема при получении курса валюты");
        }
        return result;
    }

    public void convert(double rubles, String currency) {

        double rate = getRate(currency);
        if (rate > 0) {
            System.out.println("Ваши сбережения в " + currency + ": " + rubles * rate);
        } else {
            System.out.println("Неизвестная валюта");
        }
    }
}

class FinanceApplication {
    double balance;
    HashMap<String, ArrayList<Double>> expenses;
    Converter converter;
    Scanner scanner;

    public FinanceApplication(double rubles, Scanner scanner) {
        balance = rubles;
        expenses = new HashMap<>();
        converter = new Converter();
        this.scanner = scanner;
    }

    void convert() {
        System.out.println("Ваши сбережения: " + balance + " RUB");
        System.out.println("В какую валюту хотите конвертировать? Пример: USD, EUR, JPY.");
        String currency = scanner.next();
        converter.convert(balance, currency.toUpperCase());
    }

    void saveExpense() {
        System.out.println("Введите название категории:");
        scanner.nextLine();
        String categoryName = scanner.nextLine();

        if (!expenses.containsKey(categoryName)) {
            expenses.put(categoryName, new ArrayList<Double>());
        }
        System.out.println("Введите размер траты:");
        double expense = scanner.nextDouble();
        if (balance >= expense) {
            ArrayList<Double> category = expenses.get(categoryName);
            category.add(expense);
            System.out.println("Значение сохранено!");
            balance = balance - expense;
        } else {
            System.out.println("На балансе недостаточно средств.");
        }
    }

    void printAllExpenses() {
        for (String categoryName : expenses.keySet()) {
            System.out.println(categoryName + ":");
            ArrayList<Double> expensesInCategory = expenses.get(categoryName);
            for (Double expense : expensesInCategory) {
                System.out.println("  " + expense);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер остатка на вашем счёте:");
        double balance = scanner.nextDouble();
        FinanceApplication fin = new FinanceApplication(balance, scanner);

        while (true) {
            printMenu();

            int command = scanner.nextInt();
            if (command == 0) {
                System.out.println("Выход.");
                break;
            } else if (command == 1) {
                fin.convert();
            } else if (command == 2) {
                fin.saveExpense();
            } else if (command == 3) {
                fin.printAllExpenses();
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Конвертировать валюту.");
        System.out.println("2 - Внести трату.");
        System.out.println("3 - Показать траты за неделю.");
        System.out.println("0 - Выход.");
    }
}
