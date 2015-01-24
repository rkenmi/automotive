package Client;
import java.util.Iterator;
import java.util.Scanner;
import Model.*;
//import Model.Automotive.OptionSet.Option;
import Util.Errors;

/*
Test drive consists of program prompting the user for a option set or option name. 
For option sets, the only edit available is changing the name atm.
For options, the name or price can be edited.
Duplicate names will not work properly but this is intended for the purpose of testing.
 * 
 */
	public class EditOptions extends Thread {
		private String input1, input2, input3;
		private static Automotive a1;
		boolean exists = false;
		
		EditOptions(Automotive auto){
			a1 = auto;
		}
		
		public synchronized void run()
		{
			synchronized(System.out){
				Iterator i = a1.getOptionSetNamesIterator();
				Scanner s = new Scanner(System.in);
				System.out.print("[EditOptions]Enter Option set or Option name: ");
				input1 = s.nextLine();

				String optionset = "";
			
				while(i.hasNext()) {
					optionset = i.next().toString();
					
					if( optionset.equals(input1) ){
						System.out.print("New option set name: ");
						input2 = s.nextLine();
						a1.getLHM().put(input2, a1.getOptionSet(optionset));
						a1.getOptionSet(optionset).setName(input2);
						exists = true;
						break;
					}
					else if ( a1.getOption(optionset, input1) != null){	
						System.out.print("New option name: ");
						input2 = s.nextLine();
						System.out.print("New option price: ");
						input3 = s.nextLine();
						a1.getOption(optionset, input1).setName(input2);
						a1.getOption(optionset, input2).setBaseprice(Double.parseDouble(input3));
						exists = true;
						break;
					}
				}
				if (!exists)
					System.out.println("option set/option not found");
				else{
					a1.print();
					a1.getOptionSet(optionset).printOpt();
				}
				

			}
		}
	}