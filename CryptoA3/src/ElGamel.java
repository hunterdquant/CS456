import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class ElGamel {

	public static final Random RAND = new Random(System.currentTimeMillis());

	public static final String NEW_LINE = System.getProperty("line.separator");

	public static BigInteger decrypt(BigInteger c, BigInteger h, BigInteger a, BigInteger p) {

		BigInteger f = h.modPow(a, p);
		BigInteger fInv = CryptoUtils.XGCD(p, f).getY().mod(p);
		BigInteger m = c.multiply(fInv).mod(p);
		return m;
	}

	public static Pair encrypt(BigInteger m, BigInteger g, BigInteger b, BigInteger p) {
		BigInteger u = new BigInteger(p.bitLength(), RAND);
		BigInteger h = g.modPow(u, p);
		BigInteger f = b.modPow(u, p);
		BigInteger c = f.multiply(m).mod(p);
		return new Pair(c, h);
	}

	public static void fileEncrypt(File publicKeysFile, File plainTextFile, File cipherTextFile) throws FileNotFoundException{
		Scanner keysIn = new Scanner(publicKeysFile);
		BigInteger g = new BigInteger(keysIn.nextLine());
		BigInteger b = new BigInteger(keysIn.nextLine());
		BigInteger p = new BigInteger(keysIn.nextLine());
		keysIn.close();

		Scanner plainTextIn = new Scanner(plainTextFile);
		PrintStream cipherTextOut = new PrintStream(cipherTextFile);
		while (plainTextIn.hasNextLine()) {
			String line = plainTextIn.nextLine() + NEW_LINE;
			for (int i = 0; i < line.length(); i++) {
				BigInteger m = new BigInteger(Integer.toString((int)line.charAt(i)));
				Pair c = encrypt(m, g, b, p);
				cipherTextOut.println(c.toString());
			}
		}
	}

	public static void fileDecrypt(File privateKeysFile, File cipherTextFile, File plainTextFile) throws FileNotFoundException{
		Scanner keysIn = new Scanner(privateKeysFile);
		BigInteger a = new BigInteger(keysIn.nextLine());
		BigInteger p = new BigInteger(keysIn.nextLine());
		keysIn.close();

		Scanner cipherTextIn = new Scanner(cipherTextFile);
		PrintStream plainTextOut = new PrintStream(plainTextFile);
		while (cipherTextIn.hasNextLine()) {
			String line = cipherTextIn.nextLine();
			String [] split = line.split(",");
			BigInteger c = new BigInteger(split[1]);
			BigInteger h = new BigInteger(split[0]);
			BigInteger m = decrypt(c, h, a, p);
			plainTextOut.print((char)Integer.parseInt(m.toString()));
		}
	}
}
