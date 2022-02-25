package me.henry.weather.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private static Boolean llwConfirm = false;
    private static Integer llCount = 0;

    public static void log(String info) {
        System.out.println("[" + sdf.format(new Date()) + "] [INFO] " + info);
        llwConfirm = false;
        llCount = 0;
    }

    public static void error(String info) {
        System.out.println("[" + sdf.format(new Date()) + "] [WARNING/FATAL] " + info);
        llwConfirm = false;
        llCount = 0;
    }

    public static void confirmRequest() {
        llCount += 1;
        if(llCount == 1) {
            System.out.println("[" + sdf.format(new Date()) + "] [SUCCESS] Information was successfully received.");
        } else if(llCount % 25 == 0) {
            System.out.println("\r[" + sdf.format(new Date()) + "] [SUCCESS] Information was successfully received. (" + llCount + ")");
        }
        llwConfirm = true;
    }
}
