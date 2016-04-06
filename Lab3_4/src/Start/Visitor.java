package Start;

import java.util.Random;

public class Visitor implements Runnable
{
	private Hall exhibitionHall;				// prywatna zmienna przechowywujaca referencje do sali
	private int visitingTime;					// czas zwiedzania wystawy przez goscia
	private String name;						// nazwa goscia
	
	public Visitor(String name, Hall hall)		// konstruktor
	{
		this.name = name;										// przypisanie nazwy goscia przeslanej przez argument konstruktora
		exhibitionHall = hall;									// przypisanie referencji sali wystawowej do naszej zmiennej prywatnej
		visitingTime = (new Random().nextInt(5) + 1) * 1000;	// wylosowanie ile czasu ogladac bedzie dany gosc wystawe ( 1s - 5s )
		
		exhibitionHall.addToFrontDoor(this);					// wywolanie funkcji za pomoca zmiennej reprezentujacej sale wystawowa (Hall)
																// i dodanie goscia do kolejki oczekujacej na wejscie do sali
	}
	
	public void run()
	{
		System.out.println("= = = " + Thread.currentThread().getName() + " rozpoczal ogladanie.");
		
		try									// podobnie jak w Starterze trzeba obsluzyc wyjatek bo wywolywana jest funkcja sleep
		{
			Thread.sleep(visitingTime);		// usypianie watku prze okreslona ilosc sekund (ta co byla losowana patrz wyzej)
			
			System.out.println("  " + name + " ogladal przez " + visitingTime / 1000 + "h");
		}
		catch(InterruptedException exc)
		{
			System.out.println(exc);
		}
		
		exhibitionHall.goToExitDoors(this);	// skonczylo sie czekanie wiec gosc wychodzi z sali
		
		System.out.println("= = = " + Thread.currentThread().getName() + " jest juz w domu.");
	}
	
	public String getName()
	{
		return this.name;
	}
}
