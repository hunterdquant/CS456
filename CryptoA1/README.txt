Author: Hunter Quant

Implementation:
	This is a simple substitution cipher that maps ascii characters using a matrix.
	A vector of characters is transformed by a randomly generated matrix and the resulting 
	vector is used as a character map.

Building:
	cd CryptoA1
	./build.sh

Running:
	cd src
	Keygen:
		java -cp ".:./Jama-1.0.3.jar" CryptoA1 -g <keyfile>
	Encryption:
		java -cp ".:./Jama-1.0.3.jar" CryptoA1 -e <plaintextfile> <ciphertextoutputfile> <keyfile>
	Decryption:
		java -cp ".:./Jama-1.0.3.jar" CryptoA1 -d <ciphertextfile> <plaintextoutputfile> <keyfile>

