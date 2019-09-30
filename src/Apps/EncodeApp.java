package Apps;

import Settings.MainSettings;

public class EncodeApp extends ICryptoApp {

    private String toEncode = null;
    private StringBuilder strBuffer;

    private int shift = 0;

    public EncodeApp () {
        strBuffer = new StringBuilder();

        MainSettings mainSettings = MainSettings.getInstance();
        this.shift = mainSettings.getShift();
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
                currentChar = convertChar(currentChar, shift);
            }

            strBuffer.append(currentChar);
        }
    }

    @Override
    public String GetResult() {
        return strBuffer.toString();
    }
}
