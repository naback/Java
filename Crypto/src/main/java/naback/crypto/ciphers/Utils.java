package naback.crypto.ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Utils
{
	public static byte[] generateNonceOrIv()
	{
		byte[] vector = new byte[12];
		
		SecureRandom random = new SecureRandom();
		
		random.nextBytes(vector);
		
		return vector;
	}
	
	public static SecretKey generateAES(byte[] key) throws NoSuchAlgorithmException
	{
		SecretKey sKey = new SecretKeySpec(key, "AES");
		
		return sKey;
	}
}
