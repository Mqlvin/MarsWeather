package me.henry.weather.io;

import me.henry.weather.MarsWeather;
import me.henry.weather.log.Console;

public class ProjectStructure {
    public static void generateFiles() {
        if(!MarsWeather.root.exists()) {
            if(!MarsWeather.root.mkdir()) {
                Console.error("Unable to create project folder. Exiting...");
                System.exit(-1);
            }
        }

        if(!MarsWeather.weatherLog.exists()) {
            Writers.writeFile(MarsWeather.weatherLog, "{}");
        }
    }
}
