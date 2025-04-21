package org.example.optional;
//
//Магазин конфет просит вас доработать их программную систему. На сайте магазина должен появиться поиск конфет по названию.
// Код поиска должен быть организован по такому алгоритму:
//Сначала нужно проверить, есть ли искомая конфета на складе магазина. Если конфета найдена, то возвращается информация о ней.
//Если на складе магазина конфета отсутствует — нужно проверить наличие у поставщиков.
// Если конфеты были найдены, то возвращается информация о них.
//Если конфета не была найдена, вернуть пустой результат.
//За поиск конфет на складе магазина отвечает класс Warehouse, за поиск конфет на складах поставщиков — класс SRM.
// Класс SRM поставляется с SRM-системой (SRM сокр. от англ. Supplier Relationship Management System —
// система управления взаимодействием с поставщиками) — у вас нет возможности повлиять на реализацию этого класса.
// Но можно использовать два его метода:
//listSuppliers — чтобы получить набор доступных поставщиков;
//getProduct — чтобы получить информацию о конфете по её названию и поставщику. Но если поставщик или товар отсутствуют,
// этот метод вернёт null.
//Класс SearchService будет содержать в себе общий алгоритм поиска и обращаться к классам Warehouse и SRM. Действуйте пошагово:
//Реализуйте метод search в классе Warehouse — для поиска конфеты на складе магазина.
//Реализуйте вспомогательный метод supplierSearch в классе SearchService для поиска конфеты на
// складах поставщиков с использованием объекта класса SRM. Из всех найденных конфет нужно выбрать конфету с наименьшей ценой.
// Для этого пригодится метод min из Stream API. Он находит минимальный элемент стрима при помощи объекта типа Comparator.
//Реализуйте метод search в классе SearchService для поиска конфеты на складе магазина или на складах поставщиков,
// если на складе магазина она отсутствует. Этот метод будет использоваться как входной для запуска поиска.
//Доработайте метод main класса Main, чтобы на экран выводилась нужная информация:
//
//Если конфеты найдены: Товар "[название_конфет]" доступен в количестве [количество] кг по цене [цена] руб за кг"
// (в квадратных скобках должны быть значения найденного товара).
//Если конфеты не найдены: Данный товар не найден.


import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Доработайте данный метод, чтобы на экран выводилось
        // сообщение в соответствии с заданием
        SearchService searchService = new SearchService();
        searchService.search("Мишка косолапый").ifPresentOrElse(
                candy->System.out.println("Товар ["+candy.name +"] доступен в количестве ["+candy.amount+"] кг по цене ["+candy.price+"] руб за кг"),
                ()-> System.out.println("Товар не найден")
        );
    }
}

class Candy {
    // название
    final String name;
    // цена
    final double price;
    //проданное количество
    final int amount;
    // другие варианты названия
    final Set<String> alternativeNames;

    public Candy(String name, double price, int amount, Collection<String> alternativeNames) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.alternativeNames = Set.copyOf(alternativeNames);
    }

    public Candy(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.alternativeNames = new HashSet<>();
    }
}

//содержимое этого класса не нужно менять
class SRM {
    private final Map<String, List<Candy>> suppliersProducts = new HashMap<>();

    public SRM() {
        suppliersProducts.put("Первая кондитерская фабрика", List.of(
                new Candy("Мишка на севере", 34.4, 100, Set.of("Мишка косолапый", "Мишка")),
                new Candy("Победа",56, 35, Set.of("Победа вкуса")),
                new Candy("Два верблюда",20, 47, Set.of("Каракум", "Африка")),
                new Candy("Красная шапочка",35, 3, Set.of("КРАСНАЯ ШАПОЧКА"))
        ));

        suppliersProducts.put("Триумф", List.of(
                new Candy("Мишка в лесу", 34.2, 63, Set.of("Мишка косолапый")),
                new Candy("Трюфель",21, 25, Set.of("Трюфель классический", "Трюфель шоколадный"))
        ));
        suppliersProducts.put("Сладости Везде", List.of(
                new Candy("Шоколадный восторг",33.98, 257, Collections.emptySet()),
                new Candy("Африка",25, 114, Set.of("Каракум"))
        ));
        suppliersProducts.put("ООО Дом Шоколада", List.of(
                new Candy("ШокоБомб",20, 1, Set.of("Шоко_бомб")),
                new Candy("Трюфель классический",35, 94, Set.of("Трюфель шоколадный"))
        ));

    }

    // Возвращает название всех поставщиков
    public Set<String> listSuppliers() {
        return new HashSet<>(suppliersProducts.keySet());
        // Создаём новую коллекцию на основе множества имеющихся поставщиков
        // Создание новой коллекции необходимо, чтобы клиентский код не смог
        // повлиять на содержимое, хранящееся в Map
    }

    // Возвращает информацию о товаре на складе поставщика
    // Если поставщик или товар не найден, возвращает null
    public Candy getProduct(String supplierName, String candyName) {
        return suppliersProducts.get(supplierName).stream()
                .filter(candy -> candy.name.equals(candyName) || candy.alternativeNames.contains(candyName))
                .findFirst()
                .orElse(null);
    }

    }