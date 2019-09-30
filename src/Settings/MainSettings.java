package Settings;

public class MainSettings {

    private int shift = 0;

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
}
