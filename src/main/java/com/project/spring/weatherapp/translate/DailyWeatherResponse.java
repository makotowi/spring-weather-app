package com.project.spring.weatherapp.translate;

import com.google.gson.annotations.SerializedName;
import com.project.spring.weatherapp.weatherDetales.DailyWeatherInfo;

import java.util.List;


public class DailyWeatherResponse {

    @SerializedName("hourly")
    private List<DailyWeatherInfo> dailyWeatherInfo;

    public List<DailyWeatherInfo> getDailyWeatherInfo() {
        return dailyWeatherInfo;
    }

    public void setDailyWeatherInfo(List<DailyWeatherInfo> dailyWeatherInfo) {
        this.dailyWeatherInfo = dailyWeatherInfo;
    }
}
