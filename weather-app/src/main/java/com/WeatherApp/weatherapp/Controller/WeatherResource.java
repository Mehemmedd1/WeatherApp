package com.WeatherApp.weatherapp.Controller;

import com.WeatherApp.weatherapp.Domain.WeatherRequestDetails;
import com.WeatherApp.weatherapp.Entity.WeatherResponse;
import com.WeatherApp.weatherapp.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class WeatherResource {

    private WeatherService weatherService;
        @Autowired
    public WeatherResource(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city}")
    public @ResponseBody WeatherResponse weather(@PathVariable("city")String city) throws Exception {
        final WeatherRequestDetails weatherRequestDetails= WeatherRequestDetails.builder()
                .city(city).build();
        return weatherService.getWeather(weatherRequestDetails);
    }
}
