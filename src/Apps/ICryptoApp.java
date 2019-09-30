package Apps;

public abstract class ICryptoApp {

    protected final static String alphabetUpper = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    protected final static String alphabetLower = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    protected final static int NOT_FOUND = -1;

    public abstract void SetData(String data);
    public abstract void Run();
    public abstract String GetResult();

    protected boolean isNeededChar(char current) {
        for (int i = 0; i < alphabetUpper.length(); i++) {
            if (current == alphabetUpper.charAt(i)) return true;
        }

        for (int i = 0; i < alphabetLower.length(); i++) {
            if (current == alphabetLower.charAt(i)) return true;
        }

        return false;
    }

    protected int getPosChar(String alphabet, char current) {
        for (int i = 0; i < alphabet.length(); i++) {
            if (current == alphabet.charAt(i)) return i;
        }

        return  NOT_FOUND;
    }

    protected int calcNewPosChar (int pos, int shift) {
        int size = alphabetLower.length();
        int newPos = pos + shift;

        if (newPos >= size) { newPos -= size; }
        return newPos;
    }

    protected int calcNewPosCharReverse (int pos, int shift) {
        int size = alphabetLower.length();
        int newPos = pos - shift;

        if (newPos < 0) { newPos += size; }

        return newPos;
    }

    protected char getCharFromAplhabetWithShift(String alphabet, int curPos, int shift) {
        return alphabet.charAt(calcNewPosChar(curPos, shift));
    }

    protected char getCharFromAplhabetWithShiftReverse(String alphabet, int curPos, int shift) {
        return alphabet.charAt(calcNewPosCharReverse(curPos, shift));
    }

    protected char convertChar(char current, int shift) {
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

    protected char convertCharReverse (char currentChar, int shift) {
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
}
