package config;

import java.util.HashMap;
import java.util.Map;

public class ProbabilitiesHilbertMoore {

    public static double z1 = 0.205;
    public static double z5 = 0.1;
    public static double z4 = 0.126;
    public static double z6 = 0.07;
    public static double z10 = 0.018;
    public static double z8 = 0.05;
    public static double z7 = 0.052;
    public static double z2 = 0.19;
    public static double z9 = 0.019;
    public static double z3 = 0.17;

    public static Map<String, Double> getProbabilities() {
        Map<String, Double> probs = new HashMap<>();
        probs.put("z1", z1);
        probs.put("z5", z5);
        probs.put("z4", z4);
        probs.put("z6", z6);
        probs.put("z10", z10);
        probs.put("z8", z8);
        probs.put("z7", z7);
        probs.put("z2", z2);
        probs.put("z9", z9);
        probs.put("z3", z3);
        return probs;
    }

}
