import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by hunter on 11/27/16.
 */
public class EllipticCurveElGamel {
    public static BigInteger decrypt(int  n, Point c, Point h, BigInteger prime, BigInteger a, BigInteger b) {
        Point fullMask = h;
		for (int i = 1; i < n; i++) {
			fullMask = fullMask.add(h, prime, a, b);
		}
		Point m = c.add(fullMask.negateY(prime), prime, a, b);
		return m.getX();
    }

	public static void decryptFile(int  n, BigInteger prime, BigInteger a, BigInteger b, File cipherTextFile, File plainTextFile) throws FileNotFoundException {
		Scanner in = new Scanner(cipherTextFile);
		PrintStream out = new PrintStream(plainTextFile);
		while (in.hasNextLine()) {
			String [] split = in.nextLine().split(" ");
			Point c = new Point(new BigInteger(split[0]), new BigInteger(split[1]));
			Point h = new Point(new BigInteger(split[2]), new BigInteger(split[3]));
			BigInteger m = decrypt(n, c, h, prime, a, b);
			out.print((char)Integer.parseInt(m.toString()));
		}
	}
}
