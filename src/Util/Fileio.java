package Util;

import java.io.*;
import java.util.Iterator;


import Model.*;
public class Fileio {
	
	public static Automotive BuildAutoFromFile (String filename) throws Errors{
		//Reads a textfile and returns an Automotive Object.
		//Feel free to break code into submethods
		//Please do not assign values to properties directly. Use methods in Automotive, OptionSet and Option
		
		Automotive car = new Automotive(); // create a new car from the ground up (no name, no price)
		

		int j = 0; // index for Option
		double price = 0.0;
		String OptionSet = ""; // contains the OptionSet title - e.g, Color, Transmission, etc.
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			
			boolean eof = false;
			while (!eof)
			{
				String line = buff.readLine();
						if (line == null)
							eof = true;
						else
						{
							if (line.contains("Model")) // expected to be first line of text
							{
								line = buff.readLine(); // skip to next line.. Model is not an option
								String[] fields = line.split(","); // split Car name and Car price
								price = Double.parseDouble(fields[1]);
								car.setModel(fields[0]);
								car.setBaseprice(price);
								line = buff.readLine(); // skip to next line
							}
							price = 0;
							if (line.contains("#")) // OptionSet
							{
								line = line.substring(1, line.length());
								OptionSet = line; // this line is the OptionSet title
								car.addOptionSet(OptionSet, 20); // by default, create 20 Option slots for each OptionSet

								j = 0; // reset Option index
							}
							else	// no OptionSet, must be an Option
							{
								String[] fields = line.split(",");
								
								if(fields.length > 1) // If there is a , present in line, two infos are present (Option Name, Price)
									price = Double.parseDouble(fields[1]); // store Price
								
								car.setOption(OptionSet, j, fields[0], (int)price);
								j++;
							}
						}
			} 
			buff.close();
		}
			catch (IOException e) {
				throw new Errors(1); // file name invalid
		}

		return car;
		
	}

	public static void WriteAutoObjTofile(Automotive car) throws Errors
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("who.dat"));
			out.writeObject(car);
            out.close();
		}
		catch(Exception e)
		{
			throw new Errors(2);
		}
	}
	
	public static Automotive ReadAutoObjTofile(String car) throws Errors
	{
		Automotive newcar = new Automotive(); // create a new car that will be a skeleton for the object we will load
		try
		{
		    ObjectInputStream in =  new ObjectInputStream(new FileInputStream(car));
		    newcar = (Automotive) in.readObject(); // new car has loaded the object, it's a working car now
		    in.close();
		}
		catch(Exception e)
		{
			throw new Errors(3);
		}
		
		return newcar;
	}
}

