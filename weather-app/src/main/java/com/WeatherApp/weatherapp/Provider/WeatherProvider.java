package com.WeatherApp.weatherapp.Provider;

import com.WeatherApp.weatherapp.Domain.CityCoordinates;
import com.WeatherApp.weatherapp.Domain.CityWeather;
import com.WeatherApp.weatherapp.Entity.GeocodingCoordinatesEntity;
import com.WeatherApp.weatherapp.Entity.OpenWeatherResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherProvider {
    @Value("${api.key}")
    private String apiKey;

    @Value("${weather.url}")
    private String weatherUrl;

    public OpenWeatherResponseEntity getWeather(final CityCoordinates cityCoordinates) throws Exception {
        RestTemplate restTemplate=new RestTemplate();
        final ResponseEntity<OpenWeatherResponseEntity> responseEntity;

        HttpEntity<String> requestEntity=new HttpEntity<>(null,null);
        UriComponents uriBuilder= UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lat",cityCoordinates.getLatitude())
                .queryParam("lon",cityCoordinates.getLongitude())
                .queryParam("appid",apiKey).build();
        try{
            responseEntity=restTemplate.exchange(uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity, OpenWeatherResponseEntity.class);
        }catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());
        }
        return responseEntity.getBody();

    }
}
