package logic.LZ77.coding;

import java.util.ArrayList;
import java.util.List;

public class LZ77Encoder {

    public static class CodeWord {
        public int offset;
        public int length;
        public char nextChar;

        public CodeWord(int offset, int length, char nextChar) {
            this.offset = offset;
            this.length = length;
            this.nextChar = nextChar;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d, '%c')", offset, length, nextChar);
        }
    }

    public static List<CodeWord> compress(String input, int searchBufferSize) {
        List<CodeWord> codeWords = new ArrayList<>();
        int currentIndex = 0;

        while (currentIndex < input.length()) {
            int matchOffset = 0;
            int matchLength = 0;
            char nextChar = input.charAt(currentIndex);

            int searchBufferStart = Math.max(0, currentIndex - searchBufferSize);

            for (int i = searchBufferStart; i < currentIndex; i++) {
                int length = 0;

                while (currentIndex + length < input.length() &&
                        input.charAt(i + length) == input.charAt(currentIndex + length)) {
                    length++;
                    if (i + length >= currentIndex) break;
                }

                if (length > matchLength) {
                    matchLength = length;
                    matchOffset = currentIndex - i;
                    if (currentIndex + matchLength < input.length()) {
                        nextChar = input.charAt(currentIndex + matchLength);
                    }
                }
            }

            codeWords.add(new CodeWord(matchOffset, matchLength, nextChar));

            currentIndex += matchLength + 1;
        }

        return codeWords;
    }
}
