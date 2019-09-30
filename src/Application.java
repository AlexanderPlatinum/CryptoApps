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
        ICryptoApp app = getApp(ApplicationsTypes.DECODE_APP);

        if (app == null) {
            System.out.println("Application not found!");
            return;
        }

        app.SetData(repo.Read(""));
        app.Run();

        repo.Write("", app.GetResult());
    }
}
