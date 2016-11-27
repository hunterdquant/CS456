package src;

import java.math.*;
import java.util.Random;

public class CryptoUtils {

	public static final Random rand = new Random(System.currentTimeMillis());

	public static BigInteger primeGen(int bitLength) {
		return new BigInteger(bitLength, 32, rand);
	}
	
	public static DXY XGCD(BigInteger a, BigInteger b) {
		BigInteger initA = a;
		BigInteger initB = b;
		BigInteger q = a.divide(b);
		BigInteger r = a.mod(b);
		BigInteger x1 = new BigInteger("1");
		BigInteger x2 = new BigInteger("0");
		BigInteger y1 = new BigInteger("0");
		BigInteger y2 = new BigInteger("1");
		while (!r.equals(BigInteger.ZERO)) {
			a = b;
			b = r;
			BigInteger tempX1 = x2;
			BigInteger tempY1 = y2;
			x2 = x1.subtract(q.multiply(x2));
			y2 = y1.subtract(q.multiply(y2));
			x1 = tempX1;
			y1 = tempY1;
			q = a.divide(b);
			r = a.mod(b);
		}
		return new DXY(initA.multiply(x2).add(initB.multiply(y2)), x2, y2);
	}

	public static class DXY {

		private BigInteger d;
		private BigInteger x;
		private BigInteger y;

		public DXY(BigInteger d, BigInteger x, BigInteger y) {
			this.d = d;
			this.x = x;
			this.y = y;
		}

		public BigInteger getD() {
			return d;
		}

		public BigInteger getX() {
			return x;
		}

		public BigInteger getY() {
			return y;
		}

	}
}
