import java.io.File;

public class CryptoA3 {
	
	public static void main(String [] args) {
		try {
			ElGamel.fileDecrypt(new File(args[0]), new File(args[1]), new File(args[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
