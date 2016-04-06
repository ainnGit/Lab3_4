package Start;

public class Starter {

	public static void main(String[] args) 
	{
		new Starter();
	}
	
	public Starter()
	{
		System.out.println("Main rozpoczal dzialanie");
		
		Hall exhibitionHall = new Hall(2);						// tworzenie nowej sali wystawowej z max. 2 miejscami 
		
		for(int i = 0 ; i < 5; i++)								// tu tworzyc sie bedzie 5 gosci co 1 sek
		{	
			new Visitor("Gosc_" + i, exhibitionHall);			// utworzenie nowego goscia, przeslanie referencji sali

			try													// obsluga wyjatku ktory rzuca funkcja sleep
			{
				Thread.currentThread().sleep(1000);			
			}
			catch(InterruptedException exc)						// przechwycenie tego wyjatku
			{
				System.out.println(exc);
			}
		}
	}
}
