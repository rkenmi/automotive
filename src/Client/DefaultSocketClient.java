package Client;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class 
    DefaultSocketClient 
    extends Thread implements SocketClientInterface,
                              SocketClientConstants {

    private ObjectInputStream in;
    private ObjectOutputStream out;
	Scanner s = new Scanner(System.in);
	private Object properties;
    private Socket sock;
    private String strHost;
    private int iPort;

    public DefaultSocketClient(String strHost, int iPort) {       
            setPort (iPort);
            setHost (strHost);
    }//constructor

    public synchronized void run(){
       if (openConnection()){
    	   sendOutput(properties);
    	   System.out.println( (String)getInput() );
    	   
    	   SelectCarOption s = new SelectCarOption(in, out);
    	   s.run();
    	   try {
			s.join();
	    	   //sendOutput("Block until user inputs");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    	closeSession();       
    }//run

    public synchronized boolean openConnection(){

    	   try {
    	     sock = new Socket(strHost, iPort);                    
    	   }
    	   catch(IOException socketError){
    	     if (DEBUG) System.err.println
    	        ("Unable to connect to " + strHost);
    	     return false;
    	   }
    	   try {
    	     in = new ObjectInputStream
    	      (sock.getInputStream());
    	     out = new ObjectOutputStream
    	      (sock.getOutputStream());    	     
    	   }
    	  catch (Exception e){
    	     if (DEBUG) System.err.println
    	       ("Unable to obtain stream to/from " + strHost);
    	     return false;
    	  }
    	  return true;
    	}
    
    public synchronized void handleSession(){
    	String strInput = "";
    	  if (DEBUG) System.out.println ("Handling session with "
    	            + strHost + ":" + iPort);
    	  try {
    		    while ( (strInput = in.readUTF() ) != null)
    	    handleInput (strInput);
    	  }
    	  catch (IOException e){
    	  if (DEBUG) System.out.println ("Handling session with "
    	        + strHost + ":" + iPort);
    	  }
    	}       

    public synchronized void sendOutput(Object output){
    	
    	try{
    		if(output != null)
    			this.out.writeObject(output);
    	}
    	catch (IOException e){
            if (DEBUG) System.err.println
            ("Error : cannot send output to " + strHost);	
    	}
    }
    	
    public void handleInput(String strInput){
              System.out.println(strInput);
      }
    	  
    public synchronized Object getInput(){
    	Object obj = null;
    	try{
    	obj = in.readObject();
    	}
    	catch (IOException e){
            if (DEBUG) System.err.println
            ("Error : cannot send output to " + strHost);	
    	}
    	
    	catch (ClassNotFoundException e){
            if (DEBUG) System.err.println
            ("Error : cannot send output to " + strHost);
    	}
    	
    	return obj;
    }

      public synchronized void closeSession(){
         try {

        	 System.out.println("[DefaultSocketClient]Closing session");
            in.close();
            out.close();
            sock.close();
         }
         catch (IOException e){
           if (DEBUG) System.err.println
            ("Error closing socket to " + strHost);
         }
  	   	 catch(NullPointerException n){
		   ;
  	   	 }
      }

      public void setHost(String strHost){
              this.strHost = strHost;
      }

      public void setPort(int iPort){
              this.iPort = iPort;
      }
      
      public void setData(Object p)
      {
    	  properties = p;
      }
      
      public void setOOS(ObjectOutputStream o){
    	  out = o;
      }
      
      public void setOIS(ObjectInputStream i){
    	  in = i;
      }
      
      public static void main (String arg[]){
    	   /* debug main; does daytime on local host */
    	    String strLocalHost = "";
    	  try{
    	      strLocalHost = 
    	        InetAddress.getLocalHost().getHostName();
    	  }
    	 catch (UnknownHostException e){
    	      System.err.println ("Unable to find local host");
    	 }
    	  DefaultSocketClient d = new DefaultSocketClient
    	     (strLocalHost, iDAYTIME_PORT);
    	  d.start();
    	 }

    	}// class DefaultSocketClient
      