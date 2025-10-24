package com.project.spring.weatherapp.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.spring.weatherapp.coordinationDetales.CoordinationDetales;
import com.project.spring.weatherapp.translate.GeocodingResponse;
import com.project.spring.weatherapp.translate.Translate;
import com.project.spring.weatherapp.weatherDetales.DailyWeatherInfo;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    Gson gson = new Gson();
    HttpClient httpClient;

    public Double temperature(double latitude, double  longitude) throws Exception {
        Translate translate = new Translate();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.open-meteo.com/v1/forecast?latitude=" + latitude +"&longitude="+ longitude + "&current=temperature_2m&timezone=auto&forecast_days=1"))
                .GET()
                .build();

        httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        translate = gson.fromJson(getResponse.body(), Translate.class);

        return translate.getWeatherDetales().getTemperature_2m();
    }

    public int weatherCode(double latitude, double longitude) throws Exception {
        Translate translate = new Translate();
        HttpRequest getRequest =HttpRequest.newBuilder()
                .uri(new URI("https://api.open-meteo.com/v1/forecast?latitude=" + latitude +"&longitude=" + longitude + "&current=weather_code&timezone=auto&forecast_days=1"))
                .GET()
                .build();

        httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        translate = gson.fromJson(getResponse.body(), Translate.class);
        return translate.getWeatherDetales().getWeather_code();

    }

    public List<DailyWeatherInfo> dailyWeather(double latitude, double longitude) throws Exception {
        HttpRequest getRequest =HttpRequest.newBuilder()
                .uri(new URI("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,weather_code&timezone=auto&forecast_days=1"))
                .GET()
                .build();

        httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        JsonObject root = gson.fromJson(getResponse.body(), JsonObject.class);
        JsonObject hourly = root.getAsJsonObject("hourly");

        JsonArray times = hourly.getAsJsonArray("time");
        JsonArray temps = hourly.getAsJsonArray("temperature_2m");
        JsonArray codes = hourly.getAsJsonArray("weather_code");

        List<DailyWeatherInfo> result = new ArrayList<>(times.size());

        for (int i=0; i< times.size(); i++){
            String iso = times.get(i).getAsString();
            String hhmm = (iso !=null && iso.length() >= 16) ? iso.substring(11,16) : iso;

            double temp = temps.get(i).getAsDouble();
            int code = codes.get(i).getAsInt();

            DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
            dailyWeatherInfo.setTime(hhmm);
            dailyWeatherInfo.setTemperature_2m(temp);
            dailyWeatherInfo.setWeather_code(getIcon(code));

            result.add(dailyWeatherInfo);
        }
        return result;
    }

    public List<CoordinationDetales> getCoordinations(String city) throws Exception{
        GeocodingResponse geocodingResponse = new GeocodingResponse();
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://geocoding-api.open-meteo.com/v1/search?name=" + encodedCity +"&count=10&language=en&format=json"))
                .GET()
                .build();

        httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        geocodingResponse = gson.fromJson(getResponse.body(), GeocodingResponse.class);
        return geocodingResponse.getCoordinationDetales();
    }

    public String getIcon(int weatherCode) {
        String iconUrl;
        switch (weatherCode) {
            case 0, 1:
                iconUrl = "icons/clear-sky.svg";
                break;
            case 2:
                iconUrl = "icons/partly-cloudy.svg";
                break;
            case 3:
                iconUrl = "icons/overcast.svg";
                break;
            case 45, 46, 47, 48:
                iconUrl = "icons/fog_rimeFog.svg";
                break;
            case 51, 53, 55, 56, 57:
                iconUrl = "icons/drizzle.svg";
                break;
            case 61, 63, 65, 66, 67:
                iconUrl = "icons/rain.svg";
                break;
            case 71, 73, 75, 77:
                iconUrl = "icons/snow.svg";
                break;
            case 80, 81, 82:
                iconUrl = "icons/rainshower.svg";
                break;
            case 85, 86:
                iconUrl = "icons/snowshower.svg";
                break;
            case 95, 96, 99:
                iconUrl = "icons/thunderstorms.svg";
                break;
            default:
                iconUrl = "icons/clear-sky.svg";
                break;
        }
        return iconUrl;
    }
}
