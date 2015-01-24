package Client;
import java.io.*;
import java.net.*;
import java.util.*;

import Model.*;
// Lab 6 Test Driver/Client
public class TestClient {
    public static void main(String[] args){
    	Model.Server s1 = new Model.Server();
		s1.start();
		CarModelOptionsIO t1 = new CarModelOptionsIO();
		t1.start();
		try {
			s1.turnOff();
			t1.join();
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
			System.out.println("\nDebugging\n");
			LinkedHashMap<String, Automotive> cars = s1.getContainer();
			Automotive a = cars.get("Focus Wagon ZTW");
			Iterator<OptionSet> i = a.getOptionSetNamesIterator();
			System.out.println(a.getBaseprice());
			while(i.hasNext()){
				OptionSet os = i.next() ;
				System.out.println(os.toString());
				Iterator<Option> io = os.getOptions().iterator();
				while(io.hasNext()){
					Option o = io.next();
					if(o.getName() != null )
						System.out.println( "\t" + o.getName() );
				}
			}
			

    }
}