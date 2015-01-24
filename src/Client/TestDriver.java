package Client;
import java.util.Iterator;
import java.util.Scanner;
import Model.*;
//import Model.Automotive.OptionSet.Option;
import Util.Errors;

// Lab 4 Test Driver
public class TestDriver {
	private static Automotive a1;
	public static void main(String [] args){
		
		String filename = "FordZTW.txt";
		
		try{
			a1 = Util.Fileio.BuildAutoFromFile(filename);
			a1.print();
			EditOptions e1 = new EditOptions(a1);
			EditOptions e2 = new EditOptions(a1);
			e1.start();
			e1.join();
			e2.start();
			e2.join();
		}
		catch(Errors e){
			filename = e.fixError1(); // prompt for correct file name
		}
		catch(InterruptedException u){
			u.printStackTrace();
		}
	}
}
