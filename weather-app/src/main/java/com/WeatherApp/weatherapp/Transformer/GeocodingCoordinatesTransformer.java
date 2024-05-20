package com.WeatherApp.weatherapp.Transformer;

import com.WeatherApp.weatherapp.Domain.CityCoordinates;
import com.WeatherApp.weatherapp.Entity.GeocodingCoordinatesEntity;
import org.springframework.stereotype.Service;

@Service
public class GeocodingCoordinatesTransformer {
    public CityCoordinates transformToDomain(GeocodingCoordinatesEntity entity ) {
        return CityCoordinates.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();

    }
}
