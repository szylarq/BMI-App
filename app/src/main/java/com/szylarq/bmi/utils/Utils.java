package com.szylarq.bmi.utils;

public class Utils {
    
    public static double roundDoubleValue(double value){

        return Math.round(value * 10.0)/10.0;
    }

    public static boolean isIGreaterThanZero(double input){

        return input > 0;
    }
}
