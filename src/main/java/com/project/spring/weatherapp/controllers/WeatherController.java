package com.project.spring.weatherapp.controllers;

import com.project.spring.weatherapp.coordinationDetales.CoordinationDetales;
import com.project.spring.weatherapp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String weatherApp(Model model, @RequestParam(required = false, defaultValue = "Kraków") String city) throws Exception {
        CoordinationDetales coord;
        try {
            coord = weatherService.getCoordinations(city).get(0);
            model.addAttribute("cityName", city);
        }catch (Exception e){
            System.out.println(e);
            model.addAttribute("alert", "No such city found as " + city +", displaying Kraków");
            coord = weatherService.getCoordinations("Kraków").get(0);
            model.addAttribute("cityName", "Kraków");
        }

        double lat = coord.getLatitude();
        double lon = coord.getLongitude();
        model.addAttribute("temperature", weatherService.temperature(lat, lon));
        model.addAttribute("iconPath", weatherService.getIcon(weatherService.weatherCode(lat, lon)));
        model.addAttribute("hourlyList", weatherService.dailyWeather(lat, lon));
        return "weatherApp";
    }

}
