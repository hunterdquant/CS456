package src;

import src.CryptoUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.Scanner;

public class CryptoA2 {
	public static void main(String [] args) {
		try {
			switch (args[0]) {
				case "--keygen":
					keyGen(args);
					break;
				case "-g":
					keyGen(args);
					break;
				case "--encrypt":
					encrypt(args);
					break;
				case "-e":
					encrypt(args);
					break;
				case "--decrypt":
					decrypt(args);
					break;
				case "-d":
					decrypt(args);
					break;
				default:
					System.out.printf("Usage:%n" +
							"java CryptoA2 [--keygen, -g] bitlength pubkeyFile privatekeyFile%n" +
							"java CryptoA2 [--encrypt, -e] pubkeyFile plaintextFileIn ciphertextFileOut%n" +
							"java CryptoA2 [--decrypt, -d] privatekeyFile ciphertextFileIn plaintextFileOut%n");
			}
			RSA.keyGen(4096, new File("rsapub.keys"), new File("rsaprivate.keys"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void decrypt(String [] args) throws FileNotFoundException{
		if (args.length != 4) {
			throw new IllegalArgumentException("Illegal arguments for [--decrypt, -d]");
		}
		Scanner in = new Scanner(new File(args[1]));
		BigInteger n = new BigInteger(in.nextLine());
		BigInteger d = new BigInteger(in.nextLine());
		RSA.fileDecrypt(n, d, new File(args[2]), new File(args[3]));
	}


	private static void encrypt(String [] args) throws FileNotFoundException{
		if (args.length != 4) {
			throw new IllegalArgumentException("Illegal arguments for [--encrypt, -e]");
		}
		Scanner in = new Scanner(new File(args[1]));
		BigInteger n = new BigInteger(in.nextLine());
		BigInteger e = new BigInteger(in.nextLine());
		RSA.fileEncrypt(n, e, new File(args[2]), new File(args[3]));
	}

	private static void keyGen(String [] args) throws FileNotFoundException {
		if (args.length != 4) {
			throw new IllegalArgumentException("Illegal arguments for [--keygen, -g]");
		}

		RSA.keyGen(Integer.parseInt(args[1]), new File(args[2]), new File(args[3]));
	}
}
