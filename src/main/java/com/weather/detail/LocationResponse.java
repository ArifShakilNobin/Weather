package com.weather.detail;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class LocationResponse {
    private List<LocationInformation> results;
    private double generationtime_ms;
}
