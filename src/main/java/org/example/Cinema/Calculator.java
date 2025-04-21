package org.example.Cinema;

import java.util.List;

public class Calculator {

    private Calculator() {
    }

    public static double calculate(List<MediaItem> mediaItems) {
        // Напишите реализацию метода, который будет возвращать общее количество дней,
        // потраченных на просмотр фильмов и сериалов
        double result = 0;
        for (MediaItem mediaItem : mediaItems) {
            if (mediaItem instanceof Movie movie) {
                result += movie.getRuntime();
            } else if (mediaItem instanceof Series series) {
                result += series.getRuntime() * series.getSeriesCount();
            }
        }
        return result / 60 / 24;

    }
}
