import Apps.DecodeApp;
import Apps.EncodeApp;
import Apps.ICryptoApp;
import Reposiroty.FileRepository;
import Reposiroty.IRepository;

public class Application {

    enum ApplicationsTypes {
        ENCODE_APP,
        DECODE_APP
    }

    private static ICryptoApp getApp(ApplicationsTypes type) {
        switch (type) {
            case DECODE_APP:
                return new DecodeApp();

            case ENCODE_APP:
                return new EncodeApp();

            default:
                return null;
        }
    }

    public static void main(String[] args) {

        IRepository repo = new FileRepository();
        ICryptoApp app = getApp(ApplicationsTypes.ENCODE_APP);

        if (app == null) {
            System.out.println("Application not found!");
            return;
        }

        String fileData = repo.Read("data.txt");

        if (fileData == null) {
            System.out.println("Can't read file!");
            return;
        }

        app.SetData(fileData);
        app.Run();

        if (repo.Write("out.txt", app.GetResult())) {
            System.out.println("Can't write to file!");
            return;
        }

        System.out.println("It's gonna be OK");
    }
}
