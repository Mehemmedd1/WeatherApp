package com.projects.WeatherApp_backend.model;

import lombok.Data;

@Data
public class Current {
    private double temp_c;
    private int humidity;
    private Condition condition;
}
