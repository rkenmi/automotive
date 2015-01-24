package Client;
import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Scanner;
import Model.*;

public class SelectCarOption extends Thread{
	ObjectInputStream in;
	ObjectOutputStream out;
	LinkedHashMap<String, Object> cars;
	Automotive chosen_car;
	Scanner s;
	
	SelectCarOption(ObjectInputStream i, ObjectOutputStream o) {	
			in = i;
			out = o;
	}
	
	
	public synchronized void run(){
		try {
			synchronized(System.in){
				cars = (LinkedHashMap<String, Object>) in.readObject();
				Collection<Object> c = cars.values();
				Automotive garage[] = new Automotive[c.toArray().length];
				for(int i = 0; i < c.toArray().length; i++){
					garage[i] = (Automotive) c.toArray()[i];
				}
			
				System.out.println("[SelectCarOption]Available Models: ");

				for(int i = 0; i < garage.length; i++){
					System.out.println( "\t" + garage[i].toString());
				}			
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read object from server");
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
		} 

		
		
		
	}
}
