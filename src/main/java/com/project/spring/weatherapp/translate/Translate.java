package com.project.spring.weatherapp.translate;

import com.google.gson.annotations.SerializedName;
import com.project.spring.weatherapp.weatherDetales.WeatherDetales;

public class Translate {
    @SerializedName("current")
    private WeatherDetales weatherDetales;

    public WeatherDetales getWeatherDetales() {
        return weatherDetales;
    }

    public void setWeatherDetales(WeatherDetales weatherDetales) {
        this.weatherDetales = weatherDetales;
    }
}
