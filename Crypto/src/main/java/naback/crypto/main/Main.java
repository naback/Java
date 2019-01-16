package naback.crypto.main;

import org.bouncycastle.util.encoders.Base64;

import naback.crypto.ciphers.GCM;
import naback.crypto.ciphers.Utils;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		GCM gcm = new GCM();
		
		String originalData = "Everything you can imagine is real";
		
		byte[] data = originalData.getBytes();
		byte[] aData = "Picasso".getBytes();
		byte[] iv =	Utils.generateNonceOrIv();
	    byte[] key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
	    Integer macLength = 128;
		
		byte[] encryptedData = gcm.encrypt(data, aData, iv, key, macLength);
		byte[] decryptedData = gcm.decrypt(encryptedData, aData, iv, key, macLength);
		
		System.out.println("Original Data: " + originalData);
		System.out.println("Encrypted Data: " + new String(Base64.encode(encryptedData)));
		System.out.println("Decrypted Data: " + new String(decryptedData));
	}

}
