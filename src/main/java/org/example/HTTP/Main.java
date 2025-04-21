package org.example.HTTP;//Добавьте в код сервера новый обработчик для эндпоинта /day.
// Сервер должен отвечать случайно выбранным днём недели,
// то есть одной из строк — "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN".

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

public class Main {
    private static final int PORT = 8080;


    // IOException могут сгенерировать методы create() и bind(...)
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create();


        httpServer.bind(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        httpServer.createContext("/day", new DayHandler());
// добавьте новый обработчик для /day тут
        httpServer.start(); // запускаем сервер


        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
//        httpServer.stop(1);
    }


    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("Началась обработка /hello запроса от клиента.");
            String response = "Hey! Glad to see you on our server.";
            httpExchange.sendResponseHeaders(200, 0);
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
    // объявите класс-обработчик тут
    static class DayHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
            Random random = new Random();
            int i = random.nextInt(10);
            exchange.sendResponseHeaders(200, 0);
            try(OutputStream os = exchange.getResponseBody()){
                os.write(days[i].getBytes());
            }
        }
    }
}