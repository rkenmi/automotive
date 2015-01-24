package Client;
import Adapter.*;
// Lab 2 Test Driver
public class Main {
	public static void main(String [] m)
	{
		FileInput a1 = new BuildAuto();
		a1.readFile("FordZTW.txt");
		a1.cerealize();
		a1.decerealize();
	//	a1.searchOptionSet();
		a1.searchOption();
	//	a1.
	//  a1.print();
	}
}