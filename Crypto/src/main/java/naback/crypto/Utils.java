package naback.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class Utils
{
	public Payload loadPropertiesPayload()
	{
		Properties prop = new Properties();
		InputStream input = null;
		Payload payload = null;

		try {

			input = new FileInputStream("conf/config.properties");

			// load a properties file
			prop.load(input);

			String keyString = prop.getProperty("properties.key");
			String dataString = prop.getProperty("properties.data");
			String ivString = prop.getProperty("properties.iv");
			String nonceString = prop.getProperty("properties.nonce");
			String additionalDataString = prop.getProperty("properties.additionaldata");
			Integer macLenght = Integer.parseInt(prop.getProperty("properties.maclenght"));
			
			payload = new Payload(dataString, ivString, nonceString, additionalDataString, keyString, macLenght);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return payload;
	}
	
	private void writePropertiesPayload(String sNonce, String sIv, String sAdditionalData, String sKey, Integer macLenght)
	{
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("conf/config.properties");

			// set the properties value
			prop.setProperty("properties.key", sKey);
			prop.setProperty("properties.iv", sIv);
			prop.setProperty("properties.nonce", sNonce);
			prop.setProperty("properties.additionaldata", sAdditionalData);
			prop.setProperty("properties.maclenght", Integer.toString(macLenght));

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public byte[] generateNonceOrIv()
	{
		byte[] nonceOrIv = new byte[12];
		SecureRandom random = new SecureRandom();
		
		random.nextBytes(nonceOrIv);
		
		return nonceOrIv;
	}
	
	public byte[] generateadditionalData()
	{
		byte[] additionalData = new byte[12];
		SecureRandom random = new SecureRandom();
		
		random.nextBytes(additionalData);
		
		return additionalData;
	}

	public static byte[] generateAES()
	{
		byte[] key = new byte[16];
		SecureRandom random = new SecureRandom();
		
		random.nextBytes(key);
		
		return key;
	}
	
	public SecretKey mountAESFromBase64Key(String sKey)
	{
		SecretKey secretKey = null;
		
		byte[] key = Base64.decode(sKey.getBytes());
		
		secretKey = new SecretKeySpec(key, "AES");
		
		return secretKey;
	}
	
	public void generatePayload()
	{
		byte[] nonce = generateNonceOrIv();
		byte[] iv = generateNonceOrIv();
		byte[] additionalData = generateadditionalData();
		byte[] key = generateAES();
		Integer macLenght = additionalData.length;
		
		String sNonce = new String(Base64.encode(nonce));
		String sIv = new String(Base64.encode(iv));
		String sAdditionalData = new String(Base64.encode(additionalData));
		String sKey = new String(Base64.encode(key));
		
		writePropertiesPayload(sNonce, sIv, sAdditionalData, sKey, macLenght);
	}
}
