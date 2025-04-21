package org.example.HTTPClientWeather;

public class Weather {
    DailyForecast[] DailyForecasts;

    static class DailyForecast {
        String Date;
        Temperature Temperature;

        static class Temperature {
            MinMax Minimum;
            MinMax Maximum;

            static class MinMax {
                double Value;
                String Unit;
            }
        }
    }
}
