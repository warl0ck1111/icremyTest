package com.example.icremytest.utils;

/**
 * @author Okala Bashir .O.
 */
public class Utils {

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public static boolean isBetween(String x, int lower, int upper) {
        return lower <= Integer.parseInt(x) && Integer.parseInt(x) <= upper;
    }


}
