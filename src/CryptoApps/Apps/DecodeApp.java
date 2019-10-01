package CryptoApps.Apps;

import CryptoApps.Settings.MainSettings;
import CryptoApps.Utilities.CryptoUtilities;

public class DecodeApp extends CryptoUtilities implements ICryptoApp {

    private String toAnalyze = null;
    private int[] statistics = new int[alphabetLower.length()];
    private StringBuilder strBuilder;

    public DecodeApp() {
        strBuilder = new StringBuilder();
    }

    @Override
    public void SetData(String data) {
        toAnalyze = data;
    }

    @Override
    public void Run() {
        Analyze();

        MainSettings settings = MainSettings.getInstance();
        char minRatingChar = settings.getMinimumRatingChar();

        int posMinimum = getPosMinimum();
        int posMinimumSettings = getPosCharGlobal(minRatingChar);

        int shift = calcShift(posMinimumSettings, posMinimum);

        for (int i = 0; i < toAnalyze.length(); i++) {
            char currentChar = toAnalyze.charAt(i);

            if (isNeededChar(currentChar)) {
                currentChar = convertCharReverse(currentChar, shift);
            }

            strBuilder.append(currentChar);
        }
    }

    @Override
    public String GetResult() {
        return strBuilder.toString();
    }

    private void Analyze() {
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
}
