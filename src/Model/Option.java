package Model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Option extends OptionSet implements Serializable { //Make the Option class an inner class of OptionSet (for Lab 1)
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double baseprice;

	public Option() { 
		super(); 
	}
	
	public Option(String name, double baseprice) {
		super();
		this.name = name;
		this.baseprice = baseprice;
	}
	
	public Option(String name) {
		super();
		this.name = name;
		this.baseprice = 0;
	}
	
	public Option(double baseprice) {
		super();
		this.name = "";
		this.baseprice = baseprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(double baseprice) {
		this.baseprice = baseprice;
	}
	
	//Add print method and a tostring method that is nicely formatted.
	
	public void printOpt(){
		if (name != null)
		{
			System.out.print("Option Name : "+name);
			if (baseprice != 0)
			System.out.print(",\t"+"Option Price : "+"$"+(int)baseprice);
			
			System.out.println("");
		}
	}
	
	public String toString(){
		
		String str = "";
		
		if (name != null) // for options with no price, ignore printing the price
			str = "Option Name : " + name;
		
		String price = new DecimalFormat("0.00").format(baseprice);
		
		if (baseprice != 0)
			str = "Option Name : " + name + ",\t" + "Option Price = " + price;
			
		return str;	
	}

}