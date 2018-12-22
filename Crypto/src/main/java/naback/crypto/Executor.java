package naback.crypto;

import java.util.Scanner;

public class Executor
{
	public void startCrypto()
	{	
		System.out.println("***** Welcome to Crypto *****");
		
		Scanner s = new Scanner(System.in);
		
		Integer op = 0;
		
		for(;;)
		{
			System.out.println("\n1 - CCM - Encrypt");
			System.out.println("2 - CCM - Decrypt");
			System.out.println("3 - GCM - Encrypt");
			System.out.println("4 - GCM - Decrypt");
			System.out.println("5 - Generate Random Payload");
			System.out.println("99 - Exit");
			op = s.nextInt();
			
			switch(op)
			{
			case 1:
				op = 0;
				break;
			case 2:
				op = 0;
				break;
			case 3:
				op = 0;
				break;
			case 4:
				op = 0;
				break;
			case 5:
				Utils utils = new Utils();
				utils.generatePayload();
				op = 0;
				break;
			case 99:
				return;
			}
		}
	}
}
