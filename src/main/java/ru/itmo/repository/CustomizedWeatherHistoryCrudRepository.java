package ru.itmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.WeatherHistory;

import java.util.Date;


@Repository
public interface CustomizedWeatherHistoryCrudRepository extends CrudRepository<WeatherHistory, Date> {

}
