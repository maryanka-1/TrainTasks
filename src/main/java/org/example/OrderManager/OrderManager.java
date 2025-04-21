package org.example.OrderManager;
//Вы работаете над системой управления заказами в онлайн-магазине. Ваша задача — разработать функционал
// для обработки списка заказов, где каждый заказ включает информацию о покупателе, товаре и его количестве.
//У вас есть класс Order, представляющий заказ. Ваша цель — реализовать метод, который:
// Группирует заказы по именам покупателей (имя покупателя — ключ, список заказов — значение).
// Возвращает топ-3 покупателя с наибольшим общим количеством заказанных товаров (сортировка по убыванию количества товаров).
// Подсчитывает количество различных товаров, заказанных каждым покупателем. Это будет полезно для анализа ассортимента покупок.


import java.util.*;
import java.util.stream.Collectors;

public class OrderManager {
    // Класс заказа
    static class Order {
        private final String customerName;
        private final String productName;
        private final int quantity;


        public Order(String customerName, String productName, int quantity) {
            this.customerName = customerName;
            this.productName = productName;
            this.quantity = quantity;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "customerName='" + customerName + '\'' +
                    ", productName='" + productName + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }


    // Группировка заказов по именам
//    покупателей
    public static Map<String, List<Order>> groupOrdersByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomerName()));
//        Map<String, List<Order>> result = new HashMap<>();
//        for (Order order : orders) {
//            String customerName = order.getCustomerName();
//            if (!result.containsKey(customerName)) {
//                result.put(customerName, new ArrayList<>());
//            }
//            result.get(customerName).add(order);
//        }
//        return result;
    }
    //        // Возвращает топ-3 покупателей по количеству заказанных товаров

    public static List<String> getTopCustomers(List<Order> orders) {
//        OrderComparator orderComparator = new OrderComparator();
//        List<String> names = new ArrayList<>();
//        Map<String, Integer> customerTotals = new HashMap<>();
//        for (Order order : orders) {
//            String customerName = order.getCustomerName();
//            Integer quantity = order.getQuantity();
//            if (!customerTotals.containsKey(customerName)) {
//                customerTotals.put(customerName, quantity);
//            }
//            customerTotals.put(customerName, customerTotals.get(customerName) + quantity);
//        }
//        List<Map.Entry<String, Integer>> listOfCustomers = new ArrayList<>(customerTotals.entrySet());
//        listOfCustomers.sort(orderComparator);
//        for (int i = 0; i < Math.min(3, listOfCustomers.size()); i++) {
//            names.add(i, listOfCustomers.get(i).getKey());
//        }
//        return names;
        Map<String, Integer> customerTotals = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomerName(), Collectors.summingInt(order -> order.quantity)));
        return customerTotals.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(3)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());


    }

//        // Подсчёт уникальных товаров для каждого покупателя

    public static Map<String, Integer> getDistinctProductCounts(List<Order> orders) {
//        Map<String, Integer> result = new HashMap<>();
//        Map<String, Set<String>> customersUniquePurchases = new HashMap<>();
//        for (Order order : orders) {
//            String customerName = order.getCustomerName();
//            String purchase = order.getProductName();
//            customersUniquePurchases.computeIfAbsent(customerName, K -> new HashSet<>()).add(purchase);
//        }
//        Set<Map.Entry<String, Set<String>>> entries = customersUniquePurchases.entrySet();
//        for (Map.Entry<String, Set<String>> entry : entries) {
//            result.put(entry.getKey(), entry.getValue().size());
//        }
//        return result;
        Map<String, Set<String>> customersUniquePurchases = new HashMap<>();
        orders.stream()
                .forEach(order -> customersUniquePurchases.computeIfAbsent(order.getCustomerName(), K -> new HashSet<>()).add(order.getProductName()))
        ;
        return customersUniquePurchases.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().size()));//????
    }

    public static String customerMostCountOrder(List<Order> orders) {
//        Map<String, Integer> customerCount = new HashMap<>();
//        for (Order order : orders) {
//            String name = order.getCustomerName();
//            customerCount.put(name, customerCount.getOrDefault(name, 0) + 1);
//        }
//        int max = 0;
//        String name = null;
//        for (Map.Entry<String, Integer> entry : customerCount.entrySet()) {
//            if (entry.getValue() > max) {
//                max = entry.getValue();
//                name = entry.getKey();
//            }
//        }
//        return name;
        Map<String, Integer> customerCount = new HashMap<>();
        orders.forEach(order -> {
            String name = order.getCustomerName();
            customerCount.put(name, customerCount.getOrDefault(name, 0) + 1);
        });
        return customerCount.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Alice", "Laptop", 1),
                new Order("Bob", "Smartphone", 2),
                new Order("Alice", "Tablet", 3),
                new Order("Charlie", "Headphones", 5),
                new Order("Bob", "Monitor", 1),
                new Order("Charlie", "Keyboard", 2),
                new Order("John", "Keyboard", 5)
        );

        // Группировка заказов по покупателям
        Map<String, List<Order>> groupedOrders = groupOrdersByCustomer(orders);
        System.out.println("Grouped Orders: " + groupedOrders);

        // Топ-3 покупателя
        List<String> topCustomers = getTopCustomers(orders);
        System.out.println("Top Customers: " + topCustomers);
//         Уникальные товары для каждого покупателя
        Map<String, Integer> distinctProductCounts = getDistinctProductCounts(orders);
        System.out.println("Distinct Product Counts: " + distinctProductCounts);

        System.out.println(customerMostCountOrder(orders));
    }
}