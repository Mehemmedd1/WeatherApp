package com.WeatherApp.weatherapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponseEntity {
    @JsonProperty("weather")
    private WeatherEntity[] weather;
}
