import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by hunter on 11/27/16.
 */
public class CryptoA4 {

    public static void main(String [] args) {
		try {
			Scanner in = new Scanner(new File(args[0]));
			BigInteger prime = new BigInteger(in.nextLine());
			BigInteger a = new BigInteger(in.nextLine());
			BigInteger b = new BigInteger(in.nextLine());
			String generator = in.nextLine();
			String [] parts = generator.split(",");
			Point g = new Point(new BigInteger(parts[0].substring(1)), new BigInteger(parts[1].substring(0, parts[1].length()-1)));
			String pubPoint = in.nextLine();
			parts = pubPoint.split(",");
			Point p = new Point(new BigInteger(parts[0].substring(1)), new BigInteger(parts[1].substring(0, parts[1].length()-1)));
			int n = Integer.parseInt(in.nextLine());
			EllipticCurveElGamel.decryptFile(n, prime, a, b, new File(args[1]), new File(args[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
