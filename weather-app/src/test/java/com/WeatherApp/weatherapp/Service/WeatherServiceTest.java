package com.WeatherApp.weatherapp.Service;

import com.WeatherApp.weatherapp.Domain.CityCoordinates;
import com.WeatherApp.weatherapp.Domain.WeatherRequestDetails;
import com.WeatherApp.weatherapp.Entity.GeocodingCoordinatesEntity;
import com.WeatherApp.weatherapp.Entity.OpenWeatherResponseEntity;
import com.WeatherApp.weatherapp.Entity.WeatherEntity;
import com.WeatherApp.weatherapp.Entity.WeatherResponse;
import com.WeatherApp.weatherapp.Provider.GeocodingProvider;
import com.WeatherApp.weatherapp.Provider.WeatherProvider;
import com.WeatherApp.weatherapp.Transformer.GeocodingCoordinatesTransformer;
import com.WeatherApp.weatherapp.Transformer.OpenWeatherTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherService.class)
class WeatherServiceTest {
    public static final String CITY = "London";
    public static final String LATITUDE = "23.34";
    public static final String LONGITUDE = "52.24";
    public static final String WEATHER = "Rain";
    public static final String DETAILS = "a lot of rain ";
    @MockBean
    private GeocodingProvider geocodingProvider;
    @MockBean
    private WeatherProvider weatherProvider;
    @MockBean
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    @MockBean
    private OpenWeatherTransformer openWeatherTransformer;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void test_should_return_weather_response() throws Exception {
        final WeatherRequestDetails requestDetails= WeatherRequestDetails.builder()
                .city(CITY).build();
        mockGeocodingProvider(requestDetails);
        mockGeocodingCoordinatesTransformer();
        mockWeatherProvider();
        mockOpenWeatherTransformer();
        final WeatherResponse weatherResponse=weatherService.getWeather(requestDetails);

    }

    private void mockOpenWeatherTransformer() {
        final WeatherResponse weatherResponse= WeatherResponse.builder()
                .weather(WEATHER)
                .details(DETAILS).build();
        when(OpenWeatherTransformer.transformToEntity(any())).thenReturn(weatherResponse);

        assertAll("Should return city weather response",
                ()->assertEquals(WEATHER,weatherResponse.getWeather()),
                ()->assertEquals(DETAILS,weatherResponse.getDetails()));
    }

    private void mockWeatherProvider() throws Exception {
        final WeatherEntity weather= WeatherEntity.builder()
                .main(WEATHER)
                .description(DETAILS).build();
        final WeatherEntity[] weatherEntities={weather};
        final OpenWeatherResponseEntity entity= OpenWeatherResponseEntity.builder()
                .weather(weatherEntities).build();
        when(weatherProvider.getWeather(any())).thenReturn(entity);

    }

    private void mockGeocodingCoordinatesTransformer() {
        final CityCoordinates cityCoordinates= CityCoordinates.builder()
                .longitude(LONGITUDE)
                .latitude(LATITUDE).build();
        when(geocodingCoordinatesTransformer.transformToDomain(any())).thenReturn(cityCoordinates);
    }

    private void mockGeocodingProvider(WeatherRequestDetails requestDetails) throws Exception {
        final GeocodingCoordinatesEntity entity=GeocodingCoordinatesEntity.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE).build();
        when(geocodingProvider.getCoordinates(requestDetails)).thenReturn(entity);
    }


}