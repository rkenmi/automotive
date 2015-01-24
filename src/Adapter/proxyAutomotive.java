package Adapter;

import java.util.Iterator;
import java.util.Scanner;

import Model.*;
import Util.*;

public abstract class proxyAutomotive {
	private Automotive a1; //Maybe you do not need this.
	//a1 is the hook to Model package functionality.
	
	
	public void readFile(String filename){
		//Call methods in Util to build Automotive object.
		System.out.println("ProxyAutomotive building Auto");
		do{ 
			try{
				a1 = Util.Fileio.BuildAutoFromFile(filename);
				System.out.println("Text file loaded successfully.");
			}
			catch(Errors e)
			{
				filename = e.fixError1(); // prompt for correct file name
			}
		}while(a1 == null); // repeat until file name is correct

	}
	
	public void cerealize(){
		try{	
			Util.Fileio.WriteAutoObjTofile(a1);
		}
		catch(Errors e){
			;
		}
	}
	
	public void decerealize(){
		try{	
			Automotive newFordZTW = Util.Fileio.ReadAutoObjTofile("who.dat");
		}
		catch(Errors e){
			;
		}	
	}
	
	public void searchOptionSet(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Option Set you would like to search for:");
		String input = s.nextLine();
		
		try{
		a1.getOptionSet(input).printOpt();
	//	a1.matchOptSet(input).printOpt();
		}
		catch(NullPointerException n)	{
			new Util.Errors(4); // OptionSet not found
		}
	}
	
	public void searchOption(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Option you would like to search for:");
		String input = s.nextLine();
		Iterator i = a1.getOptionSetNamesIterator();
		boolean exists = false;

		try{		
			while(i.hasNext()) {
				String option = i.next().toString();
					if ( a1.getOption(option, input) != null){ // avoids NullPointerException
						a1.getOption(option, input).printOpt();
						exists = true;
					}
				}
			if(!exists)
				throw null;
		}
		catch(NullPointerException n) {
			new Util.Errors(5); // Option not found
		}
		
	}
	
	public void print() {
		System.out.println("Printing Automotive Properties");
		a1.print();
	}
}
