import Apps.DecodeApp;
import Apps.EncodeApp;
import Apps.ICryptoApp;

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

        ICryptoApp app = getApp(ApplicationsTypes.DECODE_APP);

        if (app == null) {
            System.out.println("Application not found!");
            return;
        }

        app.SetData("");
        app.Run();

        String result = app.GetResult();

        System.out.println(result);
    }
}
