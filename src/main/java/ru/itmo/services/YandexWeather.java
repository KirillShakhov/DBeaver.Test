package ru.itmo.services;

import ru.itmo.entity.WeatherHistory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class YandexWeather {
    public static WeatherHistory getWeather(){
        try {
            URL url = new URL("https://yandex.ru/pogoda/moscow");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            StringBuilder t = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                else{
                    int index = line.indexOf("Сегодня:");
                    if(index == -1) continue;
                    for(int i = index + 9; i < line.length(); i++){
                        char tmp = line.charAt(i);
                        if(tmp == ';') break;
                        else t.append(tmp);
                    }
                }
            }
            t = new StringBuilder(t.substring(0, t.length() - 1));// remove °
            return new WeatherHistory(t.toString());
        }catch(Exception e){
            return null;
        }
    }
}
