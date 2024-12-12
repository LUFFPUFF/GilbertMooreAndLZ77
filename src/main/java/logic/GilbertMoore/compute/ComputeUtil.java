package logic.GilbertMoore.compute;

import logic.GilbertMoore.config.ConfigGilbertMoore;
import logic.GilbertMoore.util.GilbertMooreUtil;

import java.util.*;

public class ComputeUtil {

    public static void computeQAndSigma(List<Double> probabilities, List<Double> q, List<Double> sigma) {
        q.add(0.0);
        for (int i = 0; i < probabilities.size(); i++) {
            double lastQ = q.get(q.size() - 1);
            if (i > 0) {
                q.add(GilbertMooreUtil.round(lastQ + probabilities.get(i - 1), ConfigGilbertMoore.ACCURACY));
            }
            sigma.add(GilbertMooreUtil.round(q.get(q.size() - 1) + probabilities.get(i) / 2, ConfigGilbertMoore.ACCURACY));
        }
    }

    public static Map<String, String> generateCodes(List<String> zSymbols, List<Double> probabilities, List<Double> sigma) {
        Map<String, String> codes = new LinkedHashMap<>();
        List<Integer> lengths = new ArrayList<>();  // Store lengths of the codes

        for (int i = 0; i < probabilities.size(); i++) {
            double p = probabilities.get(i);
            double midpoint = sigma.get(i);

            int l = (int) Math.ceil(-Math.log(p) / Math.log(2)) + 1;
            String binaryCode = doubleToBinary(midpoint, l);

            codes.put(zSymbols.get(i), binaryCode);
            lengths.add(l);  // Store the length of each code
        }

        return codes;
    }

    public static String[][] createTable(List<String> zSymbols, List<Double> probabilities, List<Double> q, List<Double> sigma, List<Integer> lengths, Map<String, String> codes) {
        String[][] tableRows = new String[probabilities.size()][7];
        for (int i = 0; i < probabilities.size(); i++) {
            tableRows[i][0] = zSymbols.get(i);
            tableRows[i][1] = String.valueOf(probabilities.get(i));
            tableRows[i][2] = String.valueOf(q.get(i));
            tableRows[i][3] = String.valueOf(sigma.get(i));
            tableRows[i][4] = String.format("%.4f", -Math.log(probabilities.get(i)) / Math.log(2));
            tableRows[i][5] = String.valueOf(lengths.get(i));
            tableRows[i][6] = codes.get(zSymbols.get(i));
        }
        return tableRows;
    }

    public static Map<String, Double> computeStatistics(List<Double> probabilities, List<Integer> lengths) {
        double averageLength = computeAverageLength(probabilities, lengths);
        double entropy = computeEntropy(probabilities);
        double redundancy = averageLength - entropy;

        Map<String, Double> stats = new HashMap<>();
        stats.put("averageLength", averageLength);
        stats.put("entropy", entropy);
        stats.put("redundancy", redundancy);

        return stats;
    }

    public static double computeAverageLength(List<Double> probabilities, List<Integer> lengths) {
        double sum = 0;
        for (int i = 0; i < probabilities.size(); i++) {
            sum += probabilities.get(i) * lengths.get(i);
        }
        return sum;
    }

    public static double computeEntropy(List<Double> probabilities) {
        double sum = 0;
        for (double p : probabilities) {
            sum += p * (-Math.log(p) / Math.log(2));
        }
        return sum;
    }

    private static String doubleToBinary(double value, int l) {
        StringBuilder binary = new StringBuilder();
        value = value % 1;
        for (int i = 0; i < l; i++) {
            value *= 2;
            if (value >= 1) {
                binary.append("1");
                value -= 1;
            } else {
                binary.append("0");
            }
        }
        return binary.toString();
    }
}
