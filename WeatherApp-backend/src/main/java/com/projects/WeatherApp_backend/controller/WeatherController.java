package com.projects.WeatherApp_backend.controller;


import com.projects.WeatherApp_backend.model.WeatherResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class WeatherController {
    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.baseUrl}")
    private String baseUrl;

    @GetMapping("/weather")
    public Object getWeather(@RequestParam String city) {

        String url = baseUrl + "?key=" + apiKey + "&q=" + city + "&aqi=no";
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForObject(url, WeatherResponse.class);
        }catch (Exception e){
            return "Ölkə tapılmadı";
        }




    }
}
