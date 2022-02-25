package me.henry.weather;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.henry.weather.io.ProjectStructure;
import me.henry.weather.io.Reader;
import me.henry.weather.scheduler.Scheduler;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;

public class MarsWeather {
    public static final File root = new File(System.getProperty("user.dir") + "/files");
    public static final File weatherLog = new File(root + "/mars_weather.json");

    public static final DecimalFormat df = new DecimalFormat("#.##");
    public static JsonObject data = new JsonObject();

    public static void main(String[] args) {
        ProjectStructure.generateFiles();
        data = new JsonParser().parse(Objects.requireNonNull(Reader.readJson(weatherLog))).getAsJsonObject();

        Scheduler.startScheduler();
    }
}
