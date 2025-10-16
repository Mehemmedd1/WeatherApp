package com.projects.WeatherApp_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    private String name;
    private String country;
}
