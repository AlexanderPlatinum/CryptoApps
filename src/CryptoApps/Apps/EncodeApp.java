package CryptoApps.Apps;

import CryptoApps.Settings.MainSettings;
import CryptoApps.Utilities.CryptoUtilities;

public class EncodeApp extends CryptoUtilities implements ICryptoApp {

    private String toEncode = null;
    private StringBuilder strBuffer;

    private int shift = 0;

    public EncodeApp () {
        strBuffer = new StringBuilder();

        MainSettings mainSettings = MainSettings.getInstance();
        this.shift = mainSettings.getShift();
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
