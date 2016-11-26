import Jama.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by hunter on 11/16/16.
 */
public class MatrixMap {

    public static final int CHAR_SET_SIZE = 128;

    public static final String NEW_LINE = System.getProperty("line.separator");

    private double [][] key = null;


    public MatrixMap() {

    }

    /**
     * Generates a matrix key whose values are in the range [min,max]
     *
     * @param min
     * @param max
     */
    public void generateKey(int min, int max) {
        double [][] key = new double[CHAR_SET_SIZE][CHAR_SET_SIZE];
        Random rand = new Random(System.currentTimeMillis());
        boolean keyGenerated = false;
        while (!keyGenerated) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    // Generate random double between min,max
                    key[i][j] = max + (max - min) * rand.nextInt();
                }
            }
            Matrix m = new Matrix(key);
            if (m.det() != 0) {
                keyGenerated = true;
                this.key = key;
            }
        }
    }

    public String encrypt(String msg) {
        double [][] chars = new double[CHAR_SET_SIZE][1];
        for (int i = 0; i < chars.length; i++) {
            chars[i][0] = i;
        }
        Matrix b = new Matrix(key).times(new Matrix(chars));
        double [][] map = b.getArray();
        String retVal = "";
        for (int i = 0; i < msg.length(); i++) {
            retVal = retVal + (map[msg.charAt(i)][0]) + NEW_LINE;
        }
        return retVal;
    }

    public String decrypt(String msg) {
        double [][] chars = new double[CHAR_SET_SIZE][1];
        for (int i = 0; i < chars.length; i++) {
            chars[i][0] = i;
        }
        Matrix b = new Matrix(key).times(new Matrix(chars));
        double [][] map = b.getArray();
        Scanner tokenStream = new Scanner(msg);
        String retVal = "";
        while(tokenStream.hasNext()) {
            String token = tokenStream.next();
            String ch = "";
            for (int j = 0; j < map.length; j++) {
                if ((map[j][0] + "").equals(token)) {
                    ch = (char)j + "";
                }
            }
            retVal += ch;
        }
        return retVal;
    }

    public void writeKey(File file) throws FileNotFoundException {
        if (key == null) {
            throw new IllegalStateException("Cannot write key. Key not generated.");
        }
        PrintStream ps = new PrintStream(file);
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                ps.print(key[i][j] + " ");
            }
            ps.println();
        }
        ps.close();
    }

    public void readKey(File file) throws FileNotFoundException {
        key = new double[CHAR_SET_SIZE][CHAR_SET_SIZE];
        Scanner in = new Scanner(file);
        int i = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String [] vals = line.split(" ");
            for (int j = 0; j < vals.length; j++) {
                key[i][j] = Double.parseDouble(vals[j]);
            }
            i++;
        }
    }

    public void encryptFile(File textInputFile, File cipherOutputFile) throws FileNotFoundException{
        Scanner in = new Scanner(textInputFile);
        PrintStream out = new PrintStream(cipherOutputFile);

        while (in.hasNextLine()) {
            String line = in.nextLine() + NEW_LINE;
            out.println(encrypt(line));
        }
    }

    public void decryptFile(File cipherInputFile, File textOutputFile) throws FileNotFoundException{
        Scanner in = new Scanner(cipherInputFile);
        PrintStream out = new PrintStream(textOutputFile);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            out.print(decrypt(line));
        }
    }
}
