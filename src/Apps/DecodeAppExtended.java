package Apps;

import Settings.MainSettings;

import java.util.HashMap;
import java.util.Map;

public class DecodeAppExtended extends ICryptoApp {

    private String toDecode = null;
    private Map<Integer, Integer> rating = new HashMap<Integer, Integer>();
    private StringBuilder strBuilder = new StringBuilder();

    @Override
    public void SetData(String data) {
        toDecode = data;
    }

    @Override
    public void Run() {
        MainSettings settings = MainSettings.getInstance();

        Analyze();

        Integer charCombination = getMinimumKey();

        char firstCharSettings = settings.getMinCombCharFirst();
        char lastCharSettings  = settings.getMinCombCharLast();

        char firstCharKey = decodeFirstChar(charCombination);
        char lastCharKey = decodeLastChar(charCombination);

        int shiftFirst = calcShift(getPosCharGlobal(firstCharSettings), getPosCharGlobal(firstCharKey));
        int shiftLast  = calcShift(getPosCharGlobal(lastCharSettings), getPosCharGlobal(lastCharKey));

        System.out.println(firstCharKey);
        System.out.println(lastCharKey);

        System.out.println(shiftFirst);

        System.out.println(getPosCharGlobal(firstCharKey) + " " + getPosCharGlobal(firstCharSettings) );

        if (shiftFirst != shiftLast) {
            System.out.println("Keys not equals!");
            return;
        } else {
            for (int i = 0; i < toDecode.length(); i++) {
                char currentChar = toDecode.charAt(i);

                if (isNeededChar(currentChar)) {
                    currentChar = convertCharReverse(currentChar, shiftFirst);
                }

                strBuilder.append(currentChar);
            }
        }
    }

    @Override
    public String GetResult() {
        return strBuilder.toString();
    }

    private void Analyze () {
        for (int i = 0; i < toDecode.length() - 1; i++) {
            char currentChar = toDecode.charAt(i);
            char nextChar = toDecode.charAt(i + 1);

            if (isNeededChar(currentChar) && isNeededChar(nextChar)) {
                Integer key = makeKey(currentChar, nextChar);
                if (rating.containsKey(key)) {
                    Integer val = rating.get(key) + 1;
                    rating.replace(key, val);
                } else {
                    rating.put(key, 1);
                }
            }
        }
    }

    private Integer getMinimumKey() {
        Integer key = 0;
        Integer minimum = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry: rating.entrySet()) {
            Integer currentKey = entry.getKey();
            Integer currentVal = entry.getValue();

            if (currentVal < minimum) {
                key = currentKey;
            }
        }

        return key;
    }

    private Integer makeKey (char symbolOne, char symbolTwo) {
        return  ((int)symbolOne << 16) | (int)symbolTwo;
    }

    private char decodeFirstChar (int key) {
        return (char)( key >> 16 );
    }

    private char decodeLastChar (int key) {
        return (char)(key & 0xFFFF);
    }
}
