package me.henry.weather.io;

import me.henry.weather.log.Console;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    public static String readJson(File path) {
        if(path.exists()) {
            try {
                ArrayList<String> textFromFile = new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
                return String.join("", textFromFile);
            } catch(Exception e) {
                Console.error("Error while loading file: " + e);
            }
        }
        return null;
    }
}
