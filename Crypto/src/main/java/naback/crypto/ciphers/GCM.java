package naback.crypto.ciphers;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class GCM
{
	public byte[] encrypt(byte[] data, byte[] aData, byte[] iv, byte[] key, Integer macLength) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		byte[] encryptedData = null;
		
		Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
		
		SecretKey sKey = Utils.generateAES(key);
		
		GCMParameterSpec params = new GCMParameterSpec(macLength, iv);
		
		cipher.init(Cipher.ENCRYPT_MODE, sKey, params, new SecureRandom());
		
		if(aData != null)
		{
			cipher.updateAAD(aData);
		}
		
		encryptedData = cipher.doFinal(data);
		
		return encryptedData;
	}
	
	public byte[] decrypt(byte[] data, byte[] aData, byte[] iv, byte[] key, Integer macLength) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		byte[] clearData = null;
		
		Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
		
		SecretKey sKey = Utils.generateAES(key);
		
		GCMParameterSpec params = new GCMParameterSpec(macLength, iv);
		
		cipher.init(Cipher.DECRYPT_MODE, sKey, params, new SecureRandom());
		
		if(aData != null)
		{
			cipher.updateAAD(aData);
		}
		
		clearData = cipher.doFinal(data);
		
		return clearData;
	}
}
