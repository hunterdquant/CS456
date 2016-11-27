package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

	public static final String NEW_LINE = System.getProperty("line.separator");

	public static void keyGen(int bitLength, File publicKeyFile, File privateKeyFile) throws FileNotFoundException{
		BigInteger p = CryptoUtils.primeGen(bitLength);
		BigInteger q = CryptoUtils.primeGen(bitLength);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger n = p.multiply(q);
		Random rand = new Random(System.currentTimeMillis());
		BigInteger e = new BigInteger(bitLength, rand);
		CryptoUtils.DXY dxy = CryptoUtils.XGCD(phi, e);
		BigInteger d = dxy.getY();
		while (!d.multiply(e).mod(phi).equals(BigInteger.ONE)) {
			e = new BigInteger(bitLength, rand);
			dxy = CryptoUtils.XGCD(phi, e);
			d = dxy.getY();
		}
		d = d.mod(phi);
		PrintStream out = new PrintStream(publicKeyFile);
		out.println(n);
		out.println(e);
		out.close();
		out = new PrintStream(privateKeyFile);
		out.println(n);
		out.println(d);
		out.close();
	}

	public static BigInteger encrypt(BigInteger n, BigInteger e, BigInteger m) {
		return m.modPow(e, n);
	}

	public static BigInteger decrypt(BigInteger n, BigInteger d, BigInteger c) {
		return c.modPow(d, n);
	}

	public static void fileEncrypt(BigInteger n, BigInteger e, File plainText, File cipherText) throws FileNotFoundException {
		Scanner in = new Scanner(plainText);
		PrintStream out = new PrintStream(cipherText);
		while (in.hasNextLine()) {
			
			String line = in.nextLine() + NEW_LINE;
			for (int i = 0; i < line.length(); i++) {
				BigInteger c = encrypt(n, e, new BigInteger(Integer.toString((int)line.charAt(i)));
				out.println(c.toString());
			}
		}
		in.close();
		out.close();
	}

	public static void fileDecrypt(BigInteger n, BigInteger d, File cipherText, File plainText) throws FileNotFoundException{
		
		Scanner in = new Scanner(cipherText);
		PrintStream out = new PrintStream(plainText);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			BigInteger m = decrypt(n, d, new BigInteger(line));
			out.print((char)Integer.parseInt(m.toString()));
		}
		in.close();
		out.close();
	}
}
