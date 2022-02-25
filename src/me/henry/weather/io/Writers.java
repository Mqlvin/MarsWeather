package me.henry.weather.io;

import me.henry.weather.log.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Writers {
    public static void writeFile(File path, String data) {
        try {
            Files.write(path.toPath(), data.getBytes());
        } catch(IOException e) {
            Console.error("Error while writing file: " + e);
        }
    }
}
