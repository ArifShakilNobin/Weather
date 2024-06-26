package com.weather.detail;

import lombok.Data;

import java.util.List;
@Data
public class Hourly {
    private List<String> time;
    private List<Double> temperature_2m;
    private List<Double> wind_speed_10m;

    private List<Double> surface_pressure;
    private List<Double> rain;

}
