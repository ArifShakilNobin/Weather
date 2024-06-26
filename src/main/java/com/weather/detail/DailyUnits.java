package com.weather.detail;


import lombok.Data;
@Data
public class DailyUnits {
    private String time;
    private String weather_code;
    private String temperature_2m_max;
    private String rain_sum;
    private String wind_speed_10m_max;
}
