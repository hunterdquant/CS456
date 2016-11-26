import java.io.File;

/**
 * Created by hunter on 11/16/16.
 */
public class CryptoA1 {
    public static void main(String [] args) {

        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid runtime parameters. Usage: java CryptoA1 [-e, -d] <inputFile> <outputFile> <keyFile>");
        }

        MatrixMap mm = new MatrixMap();
        try {
            if ("-e".equals(args[0])) {
                mm.generateKey(0, 255);
                mm.writeKey(new File(args[3]));
                mm.encryptFile(new File(args[1]), new File(args[2]));
            } else if ("-d".equals(args[0])) {
                mm.readKey(new File(args[3]));
                mm.decryptFile(new File(args[1]), new File(args[2]));
            }
        } catch (Exception e) {
        }
    }
}
