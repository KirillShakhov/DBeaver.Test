package ru.itmo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.entity.View;
import ru.itmo.entity.WeatherHistory;
import ru.itmo.services.WeatherHistoryService;
import ru.itmo.services.YandexWeather;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class WeatherController {
    private final WeatherHistoryService weatherHistoryService;

    @Autowired
    public WeatherController(WeatherHistoryService weatherHistoryService) {
        this.weatherHistoryService = weatherHistoryService;
    }

    @JsonView(View.WeatherHistory.class)
    @GetMapping("/weather")
    public Map<String, Object> getWeather() {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Date today = java.util.Calendar.getInstance().getTime();
            Optional<WeatherHistory> weather = weatherHistoryService.getById(today);
            if(weather.isEmpty()) map.put("object", YandexWeather.getWeather());
            else map.put("object", weather.get());
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }

    @GetMapping("/weather/create")
    public Map<String, Object> createWeather(@RequestParam("t") String t) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            WeatherHistory weatherHistory = new WeatherHistory(t);
            weatherHistoryService.save(weatherHistory);
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }
}