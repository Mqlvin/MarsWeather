package me.henry.weather.scheduler;

import me.henry.weather.http.HTTPClient;
import me.henry.weather.weather.WeatherHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void startScheduler() {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        schedule.scheduleAtFixedRate(() -> {
            new Thread(() -> {
                WeatherHandler.handleResponse(HTTPClient.request("https://mars.nasa.gov/rss/api/?feed=weather&category=insight_temperature&feedtype=json&ver=1.0"));
            }).start();
        }, 0, 30, TimeUnit.MINUTES);
    }
}
