package ru.itmo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//CREATE TABLE "weather_history" (
//        "weather_date" date not null,
//        "weather_value" varchar(255) not null,
//        PRIMARY KEY ("weather_date")
//        );

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "weather_history", schema = "public")
public class WeatherHistory {
    @Id
    @Column(name = "weather_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonView(View.WeatherHistory.class)
    private Date weather_date;


    @Column(name = "weather_value", nullable = false)
    @JsonView(View.WeatherHistory.class)
    private String weather_value;

    public WeatherHistory(String t) {
        weather_date = java.util.Calendar.getInstance().getTime();
        weather_value = t;
    }

    @Override
    public String toString() {
        return "WeatherHistory{" +
                "weather_date=" + weather_date +
                ", weather_value='" + weather_value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WeatherHistory that = (WeatherHistory) o;
        return weather_date != null && Objects.equals(weather_date, that.weather_date);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
