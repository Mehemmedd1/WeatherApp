package com.projects.WeatherApp_backend.model;

import lombok.Data;


@Data
public class WeatherResponse {
    private Location location;
    private Current current;

}
