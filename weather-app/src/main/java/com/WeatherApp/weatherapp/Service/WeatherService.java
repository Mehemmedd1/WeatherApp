package com.WeatherApp.weatherapp.Service;

import com.WeatherApp.weatherapp.Domain.CityCoordinates;
import com.WeatherApp.weatherapp.Domain.CityWeather;
import com.WeatherApp.weatherapp.Domain.WeatherRequestDetails;
import com.WeatherApp.weatherapp.Entity.OpenWeatherResponseEntity;
import com.WeatherApp.weatherapp.Entity.WeatherResponse;
import com.WeatherApp.weatherapp.Provider.GeocodingProvider;
import com.WeatherApp.weatherapp.Provider.WeatherProvider;
import com.WeatherApp.weatherapp.Transformer.GeocodingCoordinatesTransformer;
import com.WeatherApp.weatherapp.Transformer.OpenWeatherTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private GeocodingProvider geocodingProvider;
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private WeatherProvider weatherProvider;
    private OpenWeatherTransformer openWeatherTransformer;
    @Autowired
    public WeatherService(final GeocodingProvider geocodingProvider,
                          final GeocodingCoordinatesTransformer geocodingCoordinatesTransformer,
                          final WeatherProvider weatherProvider,
                          final OpenWeatherTransformer openWeatherTransformer

    ) {
        this.geocodingProvider = geocodingProvider;
        this.geocodingCoordinatesTransformer = geocodingCoordinatesTransformer;
        this.weatherProvider=weatherProvider;
        this.openWeatherTransformer=openWeatherTransformer;
    }

    public  WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails)throws Exception {
        final CityCoordinates cityCoordinates=geocodingCoordinatesTransformer.transformToDomain(
                geocodingProvider.getCoordinates(weatherRequestDetails));
        final CityWeather cityWeather=openWeatherTransformer.transformToDomain(weatherProvider.
                getWeather(cityCoordinates));

        return OpenWeatherTransformer.transformToEntity(cityWeather);

    }
}
