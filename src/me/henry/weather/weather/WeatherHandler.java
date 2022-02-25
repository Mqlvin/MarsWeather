package me.henry.weather.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.henry.weather.MarsWeather;
import me.henry.weather.io.Writers;
import me.henry.weather.log.Console;
import me.henry.weather.util.Temperature;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherHandler {
    private static Type arrListType = new TypeToken<List<String>>(){}.getType();
    private static HashMap<String, String> infoTypes = new HashMap<String, String>(){{
        put("AT", "temperature");
        put("HWS", "wind_speed");
        put("PRE", "pressure");
    }};
    private static HashMap<String, String> infoNames = new HashMap<String, String>(){{
        put("min", "mn");
        put("max", "mx");
        put("avg", "av");
        put("samples", "ct");
    }};

    public static void handleResponse(String json_) {
        JsonObject json = new JsonParser().parse(json_).getAsJsonObject();
        json.remove("validity_checks");

        ArrayList<String> sols = new Gson().fromJson(json.get("sol_keys"), arrListType);
        for(String sol : sols) {
            JsonObject day = json.get(sol).getAsJsonObject();
            JsonObject daySave = new JsonObject();

            for(String type : infoTypes.keySet()) {
                JsonObject newType = new JsonObject();
                for(String val : infoNames.keySet()) {
                    if(val.equalsIgnoreCase("samples")) {
                        newType.addProperty(val, day.get(type).getAsJsonObject().get(infoNames.get(val)).toString().replace("\"", ""));
                    } else {
                        newType.addProperty(val, MarsWeather.df.format(Temperature.getCelsius(Double.parseDouble(day.get(type).getAsJsonObject().get(infoNames.get(val)).toString().replace("\"", "")))));
                    }
                }
                daySave.add(infoTypes.get(type), newType);
            }
            MarsWeather.data.add("sol" + sol, daySave);
        }
        Console.confirmRequest();
        Writers.writeFile(MarsWeather.weatherLog, new GsonBuilder().setPrettyPrinting().create().toJson(MarsWeather.data));
    }
}
