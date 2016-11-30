import java.io.File;
import java.io.FileNotFoundException;

/**
 * Runs a MatrixMap cipher on the given user arguments..
 */
public class CryptoA1 {
    public static void main(String [] args) {

        MatrixMap mm = new MatrixMap();
        try {
            if ("-e".equals(args[0])) {
                mm.readKey(new File(args[3]));
                mm.encryptFile(new File(args[1]), new File(args[2]));
            } else if ("-d".equals(args[0])) {
                mm.readKey(new File(args[3]));
                mm.decryptFile(new File(args[1]), new File(args[2]));
            } else if ("-g".equals(args[0])) {
                mm.generateKey(0, 255);
                mm.writeKey(new File(args[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
