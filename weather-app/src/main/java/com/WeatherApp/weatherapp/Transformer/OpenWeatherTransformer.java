package com.WeatherApp.weatherapp.Transformer;

import com.WeatherApp.weatherapp.Domain.CityWeather;
import com.WeatherApp.weatherapp.Entity.OpenWeatherResponseEntity;
import com.WeatherApp.weatherapp.Entity.WeatherResponse;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {
    public static WeatherResponse transformToEntity(CityWeather cityWeather) {
        return WeatherResponse.builder()
                .details(cityWeather.getDetails())
                .weather(cityWeather.getWeather()).build();


    }

    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity) {
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription()).build();

    }
}
