package com.WeatherApp.weatherapp.Transformer;

import com.WeatherApp.weatherapp.Domain.CityWeather;
import com.WeatherApp.weatherapp.Entity.GeocodingCoordinatesEntity;
import com.WeatherApp.weatherapp.Entity.OpenWeatherResponseEntity;
import com.WeatherApp.weatherapp.Entity.WeatherEntity;
import com.WeatherApp.weatherapp.Entity.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherTransformerTest {
    private OpenWeatherTransformer transformer;

    @BeforeEach
    public void setup(){
        transformer=new OpenWeatherTransformer();


    }
    @Test
    public void test_should_transform_open_weather_to_main(){
        final WeatherEntity weather= WeatherEntity.builder()
                .main("Rain")
                .description("a lot of rain ").build();
        final WeatherEntity[] weatherEntities={weather};
        final OpenWeatherResponseEntity entity= OpenWeatherResponseEntity.builder()
                .weather(weatherEntities).build();
        final CityWeather cityWeather=transformer.transformToDomain(entity);
        assertAll("should return domain weather object",()->assertEquals(entity.getWeather()[0].getMain(),cityWeather.getWeather()),
                ()->assertEquals(entity.getWeather()[0].getDescription(),cityWeather.getDetails()));
    }
    @Test
    public void test_should_transform_city_weather_to_entity(){
        final CityWeather cityWeather= CityWeather.builder()
                .weather("main")
                .details("a lot of rain").build();
        final WeatherResponse weatherResponse=transformer.transformToEntity(cityWeather);
        assertAll("should return entity weather response object",()->assertEquals(cityWeather.getWeather(),weatherResponse.getWeather())
        ,()->assertEquals(cityWeather.getDetails(),weatherResponse.getDetails()));
    }


}