package me.henry.weather.util;

public class Temperature {
    public static double getCelsius(double fahrenheit) {
        return (fahrenheit - 32) * ((double) 5 / 9);
    }
}
