package com.WeatherApp.weatherapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingCoordinatesEntity {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;
}
