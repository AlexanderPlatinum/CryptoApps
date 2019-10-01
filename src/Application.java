import Apps.*;
import Repository.FileRepository;
import Repository.IRepository;
import Settings.MainSettings;

public class Application {

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
        MainSettings mainSettings = MainSettings.getInstance();

        mainSettings.setShift(3);

        mainSettings.setMinimumRatingChar('в');

        mainSettings.setMinCombCharFirst('и');
        mainSettings.setMinCombCharLast('в');

        IRepository repo = new FileRepository();
        ICryptoApp app = getApp(AppsTypes.DECODE_APP);

        if (app == null) {
            System.out.println("Application not found!");
            return;
        }

        String fileData = repo.Read("out.txt");

        if (fileData == null) {
            System.out.println("Can't read file!");
            return;
        }

        app.SetData(fileData);
        app.Run();

        if (!repo.Write("out2.txt", app.GetResult())) {
            System.out.println("Can't write to file!");
            return;
        }

        System.out.println("It's gonna be OK");
    }
}
