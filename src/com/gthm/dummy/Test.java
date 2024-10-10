package com.gthm.dummy;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Test {

    public static void main(String[] args) {

        LocalTime localTime = LocalTime.now();
        String hourAndMinute = localTime.getHour() + ":" + localTime.getMinute();

        System.out.println(localTime);
        System.out.println(hourAndMinute);
        System.out.println(getAlpha(hourAndMinute));


    }


    public static String getAlpha(String str) {
        String toReturn = null;

        switch (str) {
            case "18:16":
                toReturn = "A";
                break;
            case "18:17":
                toReturn = "B";
                break;
            case "18:18":
                toReturn = "C";
                break;
            case "18:19":
                toReturn = "D";
                break;
            case "18:20":
                toReturn = "E";
                break;
            case "18:21":
                toReturn = "F";
                break;
        }
        return toReturn;
    }


}
