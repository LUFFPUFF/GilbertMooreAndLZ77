package logic.strngcontent;

import config.MessageLZ77;
import logic.LZ77.coding.LZ77Encoder;
import logic.configpath.ConfigFilePath;
import logic.printer.TablePrinter;
import logic.writer.Writer;

import java.util.List;

import static logic.LZ77.coding.LZ77Encoder.compress;

public class TableLZ77 {

    public static void get() {
        String input = MessageLZ77.MESSAGE;
        int searchBufferSize = 15;

        List<LZ77Encoder.CodeWord> codeWords = compress(input, searchBufferSize);

        String[] headers = {"k", "Буфер поиска", "Буфер для предварительной записи сообщения", "Кодовое слово (x, y, z)"};
        String[][] rows = new String[codeWords.size()][4];

        for (int k = 0, currentIndex = 0; k < codeWords.size(); k++) {
            LZ77Encoder.CodeWord cw = codeWords.get(k);

            int searchBufferStart = Math.max(0, currentIndex - searchBufferSize);
            String searchBuffer = input.substring(searchBufferStart, currentIndex);

            String lookaheadBuffer = input.substring(currentIndex, Math.min(input.length(), currentIndex + searchBufferSize));

            rows[k][0] = String.valueOf(k + 1);
            rows[k][1] = searchBuffer;
            rows[k][2] = lookaheadBuffer;
            rows[k][3] = cw.toString();

            currentIndex += cw.length + 1;
        }

        String table = TablePrinter.printTable(headers, rows);
        Writer.write(table, ConfigFilePath.FILE_PATH_LZ77);
    }
}
