package com.project.spring.weatherapp.weatherDetales;

public class WeatherDetales {
    private String time;
    private Double temperature_2m;
    private Integer weather_code;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(Double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public Integer getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(Integer weather_code) {
        this.weather_code = weather_code;
    }
}
