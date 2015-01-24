package Client;
import java.io.*;

import Util.LS_Properties;

public class CarModelOptionsIO extends Thread{
	String filename = "Properties.txt";
	//String filename = "http://localhost:8080/examples/Client/Properties.txt";
	String CarInfo[];
	LS_Properties props = new LS_Properties();
	DefaultSocketClient d;

	public CarModelOptionsIO(){}
	
	public CarModelOptionsIO(String s){
		filename = s;
	}

	public synchronized void run(){
		//System.out.println("[CarModelOptionsIO]" + this.getName());
		load_properties();
		try{
		d = new DefaultSocketClient("localhost", 1989);
		d.setData(props);
		d.start();
		d.join();
		}
		catch(NullPointerException n){
			System.err.println("Can't connect to server");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load_properties(){
		FileInputStream txt;
		try {
			txt = new FileInputStream(filename);
			props.load(txt);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
