package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet extends Automotive implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int choice; // used in later labs
	private ArrayList<Option> optAL;
	
	public OptionSet(){
		optAL = new ArrayList<Option>();
	}
	
	public OptionSet(String name){
		optAL = new ArrayList<Option>();
		this.name = name;
	}
	
	public OptionSet(int count){
		optAL = new ArrayList<Option>();
		for (int i = 0; i < count; i++){ // creates (count) amount of options
			optAL.add( new Option() );
		}	
	}
	
	public OptionSet(String name, int count){
		optAL = new ArrayList<Option>(); 
		for (int i = 0; i < count; i++){ // creates (count) amount of options
			optAL.add( new Option() );
		}
		this.name = name;
	}
	//Add getter and setter for all instance variables.
	//Add print method and a tostring method that is nicely formatted.
	/*  getName(): String
	 setName(s: String): void
	 getOptions(): Option[]
	 setOptions(options: Option[]): void
	 setOption(int i, String name, int price): void
	 getOption(name: String): Option
	 getOptionPrice(name: String): int
	 findOption(name: String): int */ 
	
	public String getName(){
		return name;
	}
	
	public void setName(String s){
		this.name = s;
	}

	public ArrayList<Option> getOptions(){
		return optAL;
	}
	
	public void setOptions(ArrayList<Option> options)
	{
		optAL = options;
	}
	
	public void setOption(int index, String name, int price){
		Option option = new Option(name, price);
		optAL.set(index, option);
	}
	
	public Option getOption(String name){
		int i = findOption(name); 
		
		if (i != -1) // option index found!
			return optAL.get(i);
						// otherwise..
			return null;
	}
	
	public int getOptionPrice(String name){
		int i = findOption(name);
		
		if (i != -1) // option index found!
			return (int)optAL.get(i).getBaseprice();
					// otherwise..
			return 0;
	}
	
	private int findOption(String name){
		int i;
	
		for (i=0 ; i<optAL.size(); i++)
		{
			if(optAL.get(i).getName() != null) // option of index i has a valid name
			{
					if ( optAL.get(i).getName().equals(name) ) // check the name if it matches
						return i;  // if so, return the index number
			}

		}
		return -1; // name not found
	}
	
	public void printOpt(){
		System.out.println("Option : " + name);
		
		for (int i=0; i<optAL.size(); i++){
			if (!optAL.get(i).getName().equals(""))	{
				System.out.print("\t"+"(" + i + ")");
				optAL.get(i).printOpt();
			}
		}
	}
	
	public String toString(){
		return this.name;
	}
	
	
}