package logic.strngcontent;

import config.ProbabilitiesHilbertMoore;
import logic.configpath.ConfigFilePath;
import logic.printer.TablePrinter;
import logic.writer.Writer;

import java.util.LinkedHashMap;
import java.util.Map;

import static logic.GilbertMoore.coding.GilbertMooreCoding.computeCoding;

public class TableGilbertMoore {

    public static void get() {

        StringBuilder builder = new StringBuilder();

        Map<String, Double> source = new LinkedHashMap<>();
        source.put("z1", ProbabilitiesHilbertMoore.getProbabilities().get("z1"));
        source.put("z5", ProbabilitiesHilbertMoore.getProbabilities().get("z5"));
        source.put("z4", ProbabilitiesHilbertMoore.getProbabilities().get("z4"));
        source.put("z6", ProbabilitiesHilbertMoore.getProbabilities().get("z6"));
        source.put("z10", ProbabilitiesHilbertMoore.getProbabilities().get("z10"));
        source.put("z8", ProbabilitiesHilbertMoore.getProbabilities().get("z8"));
        source.put("z7", ProbabilitiesHilbertMoore.getProbabilities().get("z7"));
        source.put("z2", ProbabilitiesHilbertMoore.getProbabilities().get("z2"));
        source.put("z9", ProbabilitiesHilbertMoore.getProbabilities().get("z9"));
        source.put("z3", ProbabilitiesHilbertMoore.getProbabilities().get("z3"));

        Map<String, Object> result = computeCoding(source);
        String[][] table = (String[][]) result.get("table");
        String[] headers = {"X", "Pm", "qm = q_i-1 + p_i-1", "σm = qi + (pi/2)", "-log2(Pm)", "lm", "bin[σ]"};

        builder.append(TablePrinter.printTable(headers, table) + "\n")
                .append("L: " + result.get("averageLength") + "\n")
                .append("H(X): " + result.get("entropy") + "\n")
                .append("r: " + result.get("redundancy"));

        Writer.write(builder.toString(), ConfigFilePath.FILE_PATH_GilbertMoore);
    }
}
