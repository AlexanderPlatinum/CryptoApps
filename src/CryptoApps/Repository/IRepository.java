package CryptoApps.Repository;

public interface IRepository {
    String Read(String fileName);
    boolean Write(String fileName, String data);
}
