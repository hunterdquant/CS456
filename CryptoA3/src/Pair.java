import java.math.BigInteger;

public class Pair {
	private BigInteger c;
	private BigInteger h;

	public Pair(BigInteger c, BigInteger h) {
		this.c = c;
		this.h = h;
	}

	public BigInteger getC() {
		return this.c;
	}

	public BigInteger getH() {
		return this.h;
	}

	public String toString() {
		return c.toString() + " " + h.toString();
	}
}

