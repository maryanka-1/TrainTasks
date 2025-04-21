package org.example.HTTPClien;

//Задание httpclient
//Доработайте программу. Выведите информацию об IP-адресе 213.186.33.69. Дополнительно выведите следующие поля:
//longitude — долгота;
//country_neighbours — соседние страны;
//country_phone — телефонный код страны.
//В качестве языка локализации укажите французский (fr).

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Main {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        // сформируйте правильный URL-адрес
        URI url = URI.create("https://ipapi.co/213.186.33.69/json/?lang=fr");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // проверяем, успешно ли обработан запрос
            if(response.statusCode() == 200) {
                JsonElement element = JsonParser.parseString(response.body());
                if(!element.isJsonObject()) {
                    System.out.println("Это не Json");
                    return;
                }


                // передаем парсеру тело ответа в виде строки, содержащей данные в формате JSON

                // проверяем, точно ли мы получили JSON-объект

                // преобразуем результат разбора текста в JSON-объект
                JsonObject jsonObject = element.getAsJsonObject();


                // получаем название страны

                // получаем название города

                // получаем значение широты
                String country = jsonObject.get("country").getAsString();
                double latitude = jsonObject.get("latitude").getAsDouble();
                String city = jsonObject.get("city").getAsString();


                // получите значения полей из задания
                double longitude = jsonObject.get("longitude").getAsDouble();
//            String countryNeighbours = jsonObject.get("country_neighbours").getAsString();
//            int countryPhone = jsonObject.get("country_phone").getAsInt();


                System.out.println("Страна: " + country);
                System.out.println("Город: " + city);
                System.out.println("Широта: " + latitude);
                System.out.println("Долгота: " + longitude);
//            System.out.println("Соседние страны: " + countryNeighbours);
//            System.out.println("Телефонный код страны: " + countryPhone);
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }
}
