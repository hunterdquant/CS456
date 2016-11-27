package src;

import java.util.*;
import java.io.*;
import java.math.*;

public class DecryptAssignment {
	
	public static void main(String [] args) {
		try {
			HashMap<String, String> cipherMap = new HashMap<String, String>();
			Scanner keys = new Scanner(new File("pub.keys.txt"));
			Scanner cipherText = new Scanner(new File("a2.cipher.txt"));
			BigInteger n = new BigInteger(keys.nextLine());
			BigInteger e = new BigInteger(keys.nextLine());
			for (int i = 0; i < 128; i++) {
				BigInteger m = new BigInteger("" + i);
				BigInteger c = m.modPow(e, n);
				cipherMap.put(c.toString(), m.toString());
			}
			PrintStream out = new PrintStream(new File("decryptedA2.txt"));
			while (cipherText.hasNextLine()) {
				out.print((char)Integer.parseInt(cipherMap.get(cipherText.nextLine())));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
