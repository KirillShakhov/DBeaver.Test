package ru.itmo.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class YandexWeather {
    public static String getWeather(){
        try {
            URL url = new URL("https://yandex.ru/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
}
