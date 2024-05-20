package com.WeatherApp.weatherapp.Entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private String weather;
    private String details;
}
