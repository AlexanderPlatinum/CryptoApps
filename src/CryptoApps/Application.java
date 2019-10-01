package CryptoApps;

import CryptoApps.Apps.*;
import CryptoApps.Repository.FileRepository;
import CryptoApps.Repository.IRepository;
import CryptoApps.Settings.MainSettings;

public class Application {

    private AppsTypes type;
    private ICryptoApp app;

    private String fileNameIn = null;
    private String fileNameOut = null;

    private IRepository repo = new FileRepository();

    public Application (AppsTypes appType) {
        type = appType;
        setupSettings();
    }

    public void SetFileNameIn (String fileName) {
        fileNameIn = fileName;
    }

    public void SetFileNameOut (String fileName) {
        fileNameOut = fileName;
    }

    public void Exec() {
        app = getApp(type);

        if (app == null) {
            System.out.println("CryptoApps.Application not found!");
            return;
        }

        String fileData = repo.Read(fileNameIn);

        if (fileData == null) {
            System.out.println("Can't read file!");
            return;
        }

        app.SetData(fileData);
        app.Run();

        if (!repo.Write(fileNameOut, app.GetResult())) {
            System.out.println("Can't write to file!");
            return;
        }

        System.out.println("It's gonna be OK");
    }

    private void setupSettings() {
        MainSettings mainSettings = MainSettings.getInstance();

        mainSettings.setShift(3);

        mainSettings.setMinimumRatingChar('в');

        mainSettings.setMinCombCharFirst('и');
        mainSettings.setMinCombCharLast('в');
    }

    private static ICryptoApp getApp(AppsTypes type) {
        switch (type) {
            case ENCODE_APP:
                return new EncodeApp();

            case DECODE_APP:
                return new DecodeApp();

            case DECODE_EXTENDED_APP:
                return new DecodeAppExtended();

            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Application app = new Application(AppsTypes.ENCODE_APP);

        app.SetFileNameIn("data.txt");
        app.SetFileNameOut("out.txt");

        app.Exec();
    }
}
