import java.math.BigInteger;

/**
 * Created by hunter on 11/27/16.
 */
public class Point {
    private BigInteger x;
    private BigInteger y;

    public Point(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point p) {
        return this.x.equals(p.x) && this.y.equals(p.y);
    }

    public Point add(Point pnt, BigInteger prime, BigInteger a, BigInteger b) {
        BigInteger m;
        if (this.equals(pnt)) {
            BigInteger denominator = new BigInteger("3").multiply(pnt.x.pow(2)).add(a).mod(prime);
            BigInteger neumerator = new BigInteger("2").multiply(pnt.y).mod(prime);
            BigInteger neumInverse = CryptoUtils.XGCD(prime, neumerator).getY();
			m = denominator.multiply(neumInverse).mod(prime);
        } else {
            BigInteger denominator = pnt.y.subtract(this.y).mod(prime);
            BigInteger neumerator = pnt.x.subtract(this.x).mod(prime);
            BigInteger neumInverse = CryptoUtils.XGCD(prime, neumerator).getY();
			m = denominator.multiply(neumInverse).mod(prime);
		}

		BigInteger x3 = m.pow(2).subtract(this.x).subtract(pnt.x).mod(prime);
		BigInteger y3 = this.y.add(m.multiply(x3.subtract(this.x))).mod(prime);

		Point retVal = new Point(x3, y3).negateY(prime);
        return retVal;
    }

	
	public Point negateY(BigInteger prime) {
		BigInteger newY = this.y.negate().mod(prime);
		return new Point(this.x, newY);
	}

	public BigInteger getX() {
		return x;
	}

	public BigInteger getY() {
		return y;
	}
}
