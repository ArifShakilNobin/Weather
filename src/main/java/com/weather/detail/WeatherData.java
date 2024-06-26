package com.weather.detail;



import lombok.Data;
@Data
public class WeatherData {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;

    private HourlyUnits hourly_units;
    private Hourly hourly;

    private DailyUnits daily_units;
    private Daily daily;
}