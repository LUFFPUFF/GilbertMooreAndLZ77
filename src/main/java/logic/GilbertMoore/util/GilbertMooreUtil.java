package logic.GilbertMoore.util;

public class GilbertMooreUtil {

    public static double round(double value, int places) {
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }
}
