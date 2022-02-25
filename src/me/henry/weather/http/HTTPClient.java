package me.henry.weather.http;

import me.henry.weather.log.Console;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HTTPClient {
    public static String request(String url_) {
        try {
            URL url = new URL(url_);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.next());
            }
            return builder.toString().replaceAll("<[^>]*>", "");
        } catch(IOException e) {
            Console.log("Exception during HTTP request: " + e);
            return null;
        }
    }
}
