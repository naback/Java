package naback.crypto;

public class Payload
{
	private final String dataString;
	private final String ivString;
	private final String nonceString;
	private final String additionalDataString;
	private final String keyString;
	private final Integer macLenght;
	
	public Payload(String dataString, String ivString, String nonceString, String additionalDataString,
			String keyString, Integer macLenght)
	{
		super();
		this.dataString = dataString;
		this.ivString = ivString;
		this.nonceString = nonceString;
		this.additionalDataString = additionalDataString;
		this.keyString = keyString;
		this.macLenght = macLenght;
	}
	public String getDataString()
	{
		return dataString;
	}
	public String getIvString()
	{
		return ivString;
	}
	public String getNonceString()
	{
		return nonceString;
	}
	public String getAdditionalDataString()
	{
		return additionalDataString;
	}
	public String getKeyString()
	{
		return keyString;
	}
	public Integer getMacLenght()
	{
		return macLenght;
	}
}
