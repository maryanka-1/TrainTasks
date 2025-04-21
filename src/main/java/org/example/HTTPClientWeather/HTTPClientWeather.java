package org.example.HTTPClientWeather;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClientWeather {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=k14gbqeuymuA2dWGTQ9cmC9XdGdmpoRU&q=Moscow");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();
        int codeOfCity=0;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                JsonElement element = JsonParser.parseString(response.body());
                if(!element.isJsonArray()){
                    System.out.println("Это не JsonArray = 1");
                    return;
                }
                JsonArray object = element.getAsJsonArray();
                for (JsonElement element1:object){
                    JsonObject jsonObject = element1.getAsJsonObject();
                    if(jsonObject.has("Key")){
                        codeOfCity = jsonObject.get("Key").getAsInt();
                    }
                }
                System.out.println("Код города = "+codeOfCity);
            }
        } catch (IOException|InterruptedException e){
            System.out.println("Проблема с запросом");
        }
        if(codeOfCity==0){
            System.out.println("Город не был определен");
            return;
        }
        url = URI.create("http://dataservice.accuweather.com/forecasts/v1/daily/5day/"+codeOfCity+"?apikey=k14gbqeuymuA2dWGTQ9cmC9XdGdmpoRU");
        request = HttpRequest.newBuilder()
                .uri(url)
                .GET().
                build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200){
                JsonElement element = JsonParser.parseString(response.body());
                if(!element.isJsonObject()){
                    System.out.println("Это не Json = 2");
                    return;
                }
                JsonObject object = element.getAsJsonObject();
                Gson gson = new Gson();
                Weather weatherResponse = gson.fromJson(object, Weather.class);
                for (Weather.DailyForecast forecast : weatherResponse.DailyForecasts) {
                    System.out.println("Дата: " + forecast.Date);
                    System.out.println("Минимальная температура: " + forecast.Temperature.Minimum.Value + " " + forecast.Temperature.Minimum.Unit);
                    System.out.println("Максимальная температура: " + forecast.Temperature.Maximum.Value + " " + forecast.Temperature.Maximum.Unit);
                    System.out.println();
                }
            }
        } catch (IOException|InterruptedException e){
            System.out.println("Проблемы с запросом погоды на 5 дней");
        }
    }
}
