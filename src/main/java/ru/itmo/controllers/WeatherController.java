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

import java.util.Map;

@RestController
public class WeatherController {
    private final WeatherHistoryService weatherHistoryService;

    @Autowired
    public WeatherController(WeatherHistoryService weatherHistoryService) {
        this.weatherHistoryService = weatherHistoryService;
    }

    @JsonView(View.WeatherHistory.class)
    @GetMapping("/weather")
    public Map<String, Object> registration(@RequestParam("login") String login,
                                            @RequestParam("pass") String pass) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            WeatherHistory weatherHistory = new WeatherHistory();


            weatherHistoryService.save(weatherHistory);

            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }
}