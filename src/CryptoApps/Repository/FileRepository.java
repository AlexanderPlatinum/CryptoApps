package CryptoApps.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileRepository implements IRepository {
    @Override
    public String Read(String fileName) {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            byte[] buffer = new byte[fin.available()];

            fin.read(buffer, 0, fin.available());

            return new String(buffer);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean Write(String fileName, String data) {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            fout.write(data.getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
