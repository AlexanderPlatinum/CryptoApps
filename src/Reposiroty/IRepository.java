package Reposiroty;

public interface IRepository {
    String Read(String fileName);
    boolean Write(String fileName, String data);
}
