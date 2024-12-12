package logic.GilbertMoore.coding;

import java.util.*;

import static logic.GilbertMoore.compute.ComputeUtil.*;

public class GilbertMooreCoding {

    public static Map<String, Object> computeCoding(Map<String, Double> source) {
        List<Double> q = new ArrayList<>();
        List<Double> sigma = new ArrayList<>();

        List<String> zSymbols = generateZSymbols(source.size());
        List<Double> probabilities = reorderProbabilities(source, zSymbols);

        computeQAndSigma(probabilities, q, sigma);

        Map<String, String> codes = generateCodes(zSymbols, probabilities, sigma);

        List<Integer> lengths = new ArrayList<>(codes.values().size());
        for (String code : codes.values()) {
            lengths.add(code.length());
        }

        String[][] tableRows = createTable(zSymbols, probabilities, q, sigma, lengths, codes);

        Map<String, Double> stats = computeStatistics(probabilities, lengths);

        Map<String, Object> result = new HashMap<>();
        result.put("table", tableRows);
        result.putAll(stats);
        return result;
    }

    private static List<String> generateZSymbols(int size) {
        List<String> symbols = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            symbols.add("z" + i);
        }
        return symbols;
    }

    private static List<Double> reorderProbabilities(Map<String, Double> source, List<String> zSymbols) {
        List<Double> reordered = new ArrayList<>();
        for (String z : zSymbols) {
            reordered.add(source.get(z));
        }
        return reordered;
    }
}
