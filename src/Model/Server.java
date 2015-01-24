package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import Util.LS_Properties;

public class Server extends Thread{
	boolean mode = true;
	private LinkedHashMap<String, Automotive> _Cars;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	BuildCarModelOptions b;
	
	public Server(){
		_Cars = new LinkedHashMap<String, Automotive>();
	} 
	
	public void run(){
		do{
		openServer();
		b = new BuildCarModelOptions();
		//in.readObject(); // for SelectCarOption
		closeServer();
		} while (mode);
	}
	public boolean openServer(){
		try {
        	serverSocket = new ServerSocket(1989);
        	System.out.println("[Server]Listening on port: 1989");
        }
		catch (IOException e) {
            System.err.println("[Server]Could not listen on port: 1989");
           	return false;
        }
		try {
            clientSocket = serverSocket.accept();
    		out = new ObjectOutputStream( clientSocket.getOutputStream() );
    		in = new ObjectInputStream( clientSocket.getInputStream()  );
        }
		catch (IOException e) {
            System.err.println("[Server]Accept failed.");
            return false;
        }
		return true;
	}
	
	public synchronized void closeServer(){
		try{
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
		}
	    catch (IOException e){
	    	System.err.println("I/O Exception");
	        System.exit(1);
		}
	}
	
	public void turnOff(){
		mode = false;
	}
	
	public LinkedHashMap<String, Automotive> getContainer(){
		return _Cars;
	}
	
	public class BuildCarModelOptions{
		
		Automotive car;
		String make, model, current_option, current_optionset;
		int i = 0;
		double price = 0;
		
		public BuildCarModelOptions(){
		    try{
		    	car = new Automotive();
		    	LS_Properties p = (LS_Properties) in.readObject();
		    	Enumeration<Object> e = p.keys();
		    	while(e.hasMoreElements() )
		    	{
		    		price = 0;
		    		String entry = (String)e.nextElement();
		    		if(entry.equals("CarMake") ){ // Make
		    			make = p.getProperty(entry);
		    			car.setMake(make);
		    		}
		    		if(entry.equals("CarModel") ){ // Model
		    			model = p.getProperty(entry);
		    			String[] fields = model.split(",");
						if(fields.length > 1) // If there is a , present in line, two infos are present (Option Name, Price)
							price = Double.parseDouble(fields[1]); // store Price
						
						model = fields[0];
		    			car.setModel(model);
		    			car.setBaseprice(price);
		    		}
		    		if(entry.contains("Option") && !entry.contains("OptionValue")){ // OptionSet
		    			current_optionset = p.getProperty(entry);
		    			car.addOptionSet(current_optionset, 20);
		    			i=0;
		    		}
		    		if(entry.contains("OptionValue") ){ // Option
		    			current_option = p.getProperty(entry);
		    			String[] fields = current_option.split(",");
						
						if(fields.length > 1) // If there is a , present in line, two infos are present (Option Name, Price)
							price = Double.parseDouble(fields[1]); // store Price
						
						car.setOption(current_optionset, i, fields[0], (int)price);
						i++;
		    		}
		    		//System.out.println(make+model);
		    	}
		    	
		    	_Cars.put(model, car);
		    	out.writeObject("[Server]Car is created successfully!");
		    	out.writeObject(_Cars);
		    	
		    }
		    catch (IOException e){
	           System.err.println("I/O Exception");
	           System.exit(1);
		    }
		    catch (ClassNotFoundException c){
	           System.err.println("ClassNotFound Exception");
	           System.exit(1);
		    }
		}
	}
}
