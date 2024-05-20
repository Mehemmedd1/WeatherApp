package com.WeatherApp.weatherapp.Transformer;

import com.WeatherApp.weatherapp.Domain.CityCoordinates;
import com.WeatherApp.weatherapp.Entity.GeocodingCoordinatesEntity;
import org.hibernate.query.sqm.internal.SqmInterpretationsKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeocodingCoordinatesTransformerTest {
    private GeocodingCoordinatesTransformer transformer;

    @BeforeEach
    public void setup(){
        transformer=new GeocodingCoordinatesTransformer();


    }
    @Test
    public void test_should_transform_geocoding_coordinates_to_main(){
        final GeocodingCoordinatesEntity entity=GeocodingCoordinatesEntity
                .builder()
                .longitude("12.0")
                .latitude("-12.33").build();
        final CityCoordinates cityCoordinates=transformer.transformToDomain(entity);
        assertAll("Should return domain city coordinates",()->assertEquals(entity.getLatitude(),cityCoordinates.getLatitude()),
        ()->assertEquals(entity.getLongitude(),cityCoordinates.getLongitude()));
    }

}