package ee.decoder.hexastring;

import ee.decoder.hexastring.helpers.Bin2CharHashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
    private static final HashMap<String, String> bin2Chars = Bin2CharHashmap.bin2CharacterHashMap();
    private String hexString;
    private final StringBuilder result = new StringBuilder();
    public Decoder(String hexString) {
        this.hexString = hexString;
        this.decode();
    }

    private void decode() {
        int currentCharI = 0;
        boolean foundFirstNonNull = false;
        String[] finalChars = new String[this.hexString.length() / 2];
        char[] chars = this.hexString.toCharArray();
        char[] binaryNumbers = new char[this.hexString.length() * 4];
        for (int i = chars.length - 1; i >= 0; i--) {
            int numericValue = Character.digit(chars[i], 16);
            binaryNumbers[currentCharI * 4] = (numericValue & 0x8) > 0 ? '1' : '0';
            binaryNumbers[currentCharI * 4 + 1] = (numericValue & 0x4) > 0 ? '1' : '0';
            binaryNumbers[currentCharI * 4 + 2] = (numericValue & 0x2) > 0 ? '1' : '0';
            binaryNumbers[currentCharI * 4 + 3] = (numericValue & 0x1) > 0 ? '1' : '0';
            currentCharI++;
        }
        int currentResultIndex = finalChars.length - 1;
        StringBuilder currentBinStr = new StringBuilder();
        for (char c : binaryNumbers) {
            if (!foundFirstNonNull) {
                if (c != '1') continue;
                else foundFirstNonNull = true;
            }
            currentBinStr.append(c);
            if (currentBinStr.length() == 7) {
                finalChars[currentResultIndex] = bin2Chars.get(String.valueOf(currentBinStr));
                currentBinStr = new StringBuilder();
                currentResultIndex--;
            }
        }
        for (String str : finalChars) {
            this.result.append(str);
        }
    }

    public String getResult() {
        return this.result.toString();
    }
}
