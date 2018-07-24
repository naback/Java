package http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpRequest 
{
	private static String sessionKey;

	public static String post(String url, String jsonRequest) throws Exception 
	{
		try
		{
			Client client = Client.create();
			
			WebResource webResource = client.resource(url);
			
			ClientResponse response = webResource.
					accept("application/json").
					type("application/json").
					header("x-mysolomeo-session-key", sessionKey).
					post(ClientResponse.class, jsonRequest);
			
			if (response.getStatus() != 200) 
			{				
				String error = response.getEntity(String.class);
				throw new Exception("Failed. HTTP Status [" + response.getStatus() + "] Error [" + error + "]");
	        }
			
			String jsonResponse = response.getEntity(String.class);

			System.out.println("Sending 'POST' request to URL: " + url);
			System.out.println("Response code: " + response.getStatus());
			
			return jsonResponse;
		}
		catch (Exception ex)
		{
			throw new Exception("Error on post.", ex);
		}
	}
	
	public static String get(String url) throws Exception 
	{		
		try
		{
			Client client = Client.create();			
			
			WebResource webResource = client.resource(url);
			
			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
			
			if (response.getStatus() != 200) 
			{				
				String error = response.getEntity(String.class);
				throw new Exception("Failed. HTTP Status [" + response.getStatus() + "] Error [" + error + "]");
	        }
			
			String jsonResponse = response.getEntity(String.class);			
			
			System.out.println("\nSending 'GET' request to URL: " + url);
			System.out.println("Response Code: " + response.getStatus());
			
			return jsonResponse;
		}
		catch (Exception ex)
		{
			throw new Exception("Error on get.", ex);
		}
	}
}
