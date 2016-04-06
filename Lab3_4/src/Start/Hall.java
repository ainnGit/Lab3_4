package Start;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Hall 
{
	private int maxVisitors;					// maksymalna liczba odwiedzajacych wystawe w danej chwili
	
	public List<Visitor> currentVisitors = new ArrayList<Visitor>();		// osoby przebywajace na wystawie
	public Queue<Visitor> frontDoor = new ArrayDeque<Visitor>();			// osoby oczekujace na wejscie 
	
	public Hall(int maxVisitors)				// konstruktor
	{
		this.maxVisitors = maxVisitors;
	}
	
	public synchronized void addToFrontDoor(Visitor visitor)
	{
		System.out.println("= = = " + visitor.getName() + " stoi przed wejsciem.");
		frontDoor.add(visitor);			// dodanie goscia do osob oczekujacych na wejscie

		checkEmptySeats(visitor);		// sprawdzenie czy gosc moze wejsc bo liczba miejsc w srodku jest ograniczona ( do 2 - patrz Starter)
	}
	
	public void checkEmptySeats(Visitor visitor)
	{
		if(currentVisitors.size() < maxVisitors)		// sprawdzenie czy ilosc osob na sali jest mniejsza od dopuszczalnej (2)
		{
			System.out.println("\t " + visitor.getName() + " wlasnie wszedl do sali.");
			
			currentVisitors.add(frontDoor.poll());		// jesli ta to dodaje sie danego goscia do osob przebywajacych na sali
					
			getRaport();								// zwyle komunikaty wypisywane na konsoli
			
			new Thread(visitor, visitor.getName()).start();		// utworzenie nowego watku i wywolanie jego metody run ( czyli  tego danego goscia )
		}
		else											// jesli nie ma wolnych miejsc wypisywany jest komunikat o braku mieksc
		{
			System.out.println("\t\t Za malo miejsc. Trzeba czekac");
			getRaport();
		}
	}
	
	private void getRaport()
	{
		System.out.println("\t\t Przed wejsciem jest aktualnie: " + frontDoor.size() + " osob oczekujacych.");
		System.out.println("\t\t Aktualnie na sali przebywa: " + currentVisitors.size() + " osob.");
	}
	
	public synchronized void goToExitDoors(Visitor visitor)
	{
		System.out.println(visitor.getName() + " opuszcza sale wystawowa.");
		
		currentVisitors.remove(visitor);			// po wyjsciu goscia usuwamy go z listy aktualnie przybywajacych na sali
		
		if(frontDoor.size() > 0)					// sprawdzenie czy ktos czeka w kolejce
			checkEmptySeats(frontDoor.peek());		// jesli tak to wysyla referencje goscia ktory jest pierwszy w kolejce i sprawdza czy sa wolne miejsca na sali
	}
}
