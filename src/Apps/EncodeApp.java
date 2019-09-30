package Apps;

public class EncodeApp implements ICryptoApp {

    private String toEncode = null;
    private StringBuilder strBuffer = null;

    private final static String alphabetUpper = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private final static String alphabetLower = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private final static int NOT_FOUND = -1;

    private int shift = 0;

    public EncodeApp () {
        strBuffer = new StringBuilder();
    }

    public int GetShift() {
        return shift;
    }

    public void SetShift(int shift) {
        this.shift = shift;
    }

    @Override
    public void SetData(String data) {
        toEncode = data;
    }

    @Override
    public void Run() {
        for (int i = 0; i < toEncode.length(); i++) {

            char currentChar = toEncode.charAt(i);

            if (isNeededChar(currentChar)) {
                currentChar = convertChar(currentChar);
            }

            strBuffer.append(currentChar);

        }
    }

    @Override
    public String GetResult() {
        return strBuffer.toString();
    }

    private boolean isNeededChar(char current) {
        for (int i = 0; i < alphabetUpper.length(); i++) {
            if (current == alphabetUpper.charAt(i)) return true;
        }

        for (int i = 0; i < alphabetLower.length(); i++) {
            if (current == alphabetLower.charAt(i)) return true;
        }

        return false;
    }

    private char convertChar(char current) {
        int posUpper = getPosChar(alphabetUpper, current);
        if (posUpper != NOT_FOUND) {
            return getCharFromAplhabetWithShift(alphabetUpper, posUpper, shift);
        }

        int posLower = getPosChar(alphabetLower, current);
        if (posLower != NOT_FOUND) {
            return getCharFromAplhabetWithShift(alphabetLower, posLower, shift);
        }

        return current;
    }

    private int getPosChar(String alphabet, char current) {
        for (int i = 0; i < alphabet.length(); i++) {
            if (current == alphabet.charAt(i)) return i;
        }

        return  NOT_FOUND;
    }

    private int calcNewPosChar (int pos, int shift) {
        int size = alphabetLower.length();
        int newPos = pos + shift;

        if (newPos > size) { newPos -= size; }
        return newPos;
    }

    private char getCharFromAplhabetWithShift(String alphabet, int curPos, int shift) {
        return alphabet.charAt(calcNewPosChar(curPos, shift));
    }
}
