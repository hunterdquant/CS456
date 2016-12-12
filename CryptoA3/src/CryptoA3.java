
import java.util.Arrays;
import java.util.Random;

public class CryptoA3 {
	
	public static void main(String [] args) {
		Random rand = new Random(System.currentTimeMillis());
		long key = rand.nextLong();
		String keyS = Long.toBinaryString(key);
		keyS = getNeededZeros(keyS, 64) + keyS;
		String[] subkeys = DES.generateSubKeys(keyS);
		String msg = "Hello!bb";
		msg = toBitString(msg.getBytes(), 8);
		System.out.println(msg);
		String enc = DES.encrypt(msg, subkeys);
		String dec = DES.decrypt(enc, subkeys);
		System.out.println(bitStingToMsg(dec));
		System.out.println();
	}

	private static String toBitString(byte [] bytes, int bytePadding) {
		String retVal = "";
		for (byte b : bytes) {
			String bits = Integer.toBinaryString(b);
			bits = getNeededZeros(bits, bytePadding) + bits;
			retVal = retVal + bits;
		}
		return retVal;
	}

	private static String getNeededZeros(String bits, int bytePadding) {
		String zeros = "";
		for (int i = 0; i < bytePadding - bits.length(); i++) {
			zeros += '0';
		}
		return zeros;
	}

	private static String bitStingToMsg(String bitString) {
		String retVal = "";
		for (int i = 0; i < bitString.length()/8; i++) {
			String b = bitString.substring(i*8, i*8 + 8);
			retVal += (char)DES.getIntValue(b);
		}
		return retVal;
	}
}
