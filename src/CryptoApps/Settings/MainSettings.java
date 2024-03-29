package CryptoApps.Settings;

public class MainSettings {

    private int shift = 0;
    private char minimumRatingChar = '\0';

    private char minCombCharFirst = '\0';
    private char minCombCharLast  = '\0';

    private static MainSettings instance = null;

    private MainSettings() {}

    public static MainSettings getInstance() {
        if (instance == null) {
            instance = new MainSettings();
        }

        return instance;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public char getMinimumRatingChar() {
        return minimumRatingChar;
    }

    public void setMinimumRatingChar(char minimumRatingChar) {
        this.minimumRatingChar = minimumRatingChar;
    }

    public char getMinCombCharFirst() {
        return minCombCharFirst;
    }

    public void setMinCombCharFirst(char minCombCharFirst) {
        this.minCombCharFirst = minCombCharFirst;
    }

    public char getMinCombCharLast() {
        return minCombCharLast;
    }

    public void setMinCombCharLast(char minCombCharLast) {
        this.minCombCharLast = minCombCharLast;
    }
}
