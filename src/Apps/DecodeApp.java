package Apps;

import Settings.MainSettings;

public class DecodeApp extends ICryptoApp {

    private String toAnalyze = null;
    private int[] statistics = new int[alphabetLower.length()];
    private StringBuilder strBuffer;

    public DecodeApp() {
        strBuffer = new StringBuilder();
    }

    @Override
    public void SetData(String data) {
        toAnalyze = data;
    }

    @Override
    public void Run() {
        int shift = Analyze();

        for (int i = 0; i < toAnalyze.length(); i++) {
            char currentChar = toAnalyze.charAt(i);

            if (isNeededChar(currentChar)) {
                currentChar = convertChar(currentChar, shift);
            }

            strBuffer.append(currentChar);
        }
    }

    @Override
    public String GetResult() {
        return strBuffer.toString();
    }

    private char convertChar (char currentChar, int shift) {
        int posUpper = getPosChar(alphabetUpper, currentChar);
        if (posUpper != NOT_FOUND) {
            currentChar = getCharFromAplhabetWithShiftReverse(alphabetUpper, posUpper, shift);
        }

        int posLower = getPosChar(alphabetLower, currentChar);
        if (posLower != NOT_FOUND) {
            currentChar = getCharFromAplhabetWithShiftReverse(alphabetLower, posLower, shift);
        }

        return currentChar;
    }

    private int Analyze() {
        for (int i = 0; i < toAnalyze.length(); i++) {

            char currentChar = toAnalyze.charAt(i);

            if (isNeededChar(currentChar)) {
                int posUpper = getPosChar(alphabetUpper, currentChar);
                if (posUpper != NOT_FOUND) {
                    statistics[posUpper]++;
                }

                int posLower = getPosChar(alphabetLower, currentChar);
                if (posLower != NOT_FOUND) {
                    statistics[posLower]++;
                }
            }
        }

        int posMinimum = getPosMinimum();
        int posMinimumSettings = getPosMinimumFromSettings();

        return calcShift(posMinimumSettings, posMinimum);
    }

    private int getPosMinimum () {
        int pos = 0;
        int minimumValue = Integer.MAX_VALUE;

        for (int i = 0; i < alphabetLower.length(); i++) {
            if (statistics[i] < minimumValue && statistics[i] != 0) {
                minimumValue = statistics[i];
                pos = i;
            }
        }

        return pos;
    }

    private int calcShift(int minimumEncoded, int minimumDecoded) {
        int shift = minimumDecoded - minimumEncoded;

        if ( shift < 0 ) {
            shift += alphabetLower.length();
        }

        return shift;
    }

    private int getPosMinimumFromSettings () {
        MainSettings settings = MainSettings.getInstance();
        char currentChar = settings.getMinimumRatingChar();

        int lowerPos = getPosChar(alphabetLower, currentChar);
        if (lowerPos != NOT_FOUND) {
            return lowerPos;
        }

        int upperPos = getPosChar(alphabetUpper, currentChar);
        if (upperPos != NOT_FOUND) {
            return upperPos;
        }

        return NOT_FOUND;
    }
}
