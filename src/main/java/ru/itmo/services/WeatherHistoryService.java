package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.entity.WeatherHistory;
import ru.itmo.repository.CustomizedWeatherHistoryCrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class WeatherHistoryService {
    private final CustomizedWeatherHistoryCrudRepository customizedWeatherHistoryCrudRepository;

    @Autowired
    public WeatherHistoryService(CustomizedWeatherHistoryCrudRepository customizedWeatherHistoryCrudRepository) {
        this.customizedWeatherHistoryCrudRepository = customizedWeatherHistoryCrudRepository;
    }

    @Transactional
    public List<WeatherHistory> findAll() {
        return (List<WeatherHistory>) customizedWeatherHistoryCrudRepository.findAll();
    }

    @Transactional
    public void save(WeatherHistory user) {
        customizedWeatherHistoryCrudRepository.save(user);
    }

    @Transactional
    public Optional<WeatherHistory> getById(Date date) {
        return customizedWeatherHistoryCrudRepository.findById(date);
    }
}

