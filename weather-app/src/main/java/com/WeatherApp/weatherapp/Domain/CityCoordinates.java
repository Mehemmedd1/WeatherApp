package com.WeatherApp.weatherapp.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Builder
@Setter
@Getter
public class CityCoordinates {
    private String latitude;
    private String longitude;
}
