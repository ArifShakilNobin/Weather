package com.weather.detail;

import lombok.Data;

import java.util.List;
@Data
public class Daily {
    private List<String> time;
    private List<Integer> weather_code;
    private List<Double> wind_speed_10m_max;

private List<Double> temperature_2m_max;
private List<Double> rain_sum;



}
