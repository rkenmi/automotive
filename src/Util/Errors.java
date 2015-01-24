package Util;

import java.util.Scanner;
//import java.util.Arrays;

public class Errors extends Exception {
	private int errorno;
	private String errormsg;
	
	public Errors() {
		super();
		printmyerror();
	}
	
	public Errors(String errormsg) {
		super();
		this.errormsg = errormsg;
		printmyerror();
	}
	
	public Errors(int errorno) {
		super();
		this.errorno = errorno;
		printmyerror();
	}
	
	public Errors(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		printmyerror();
	}
	
	public int getErrorno() {
		return errorno;
	}
	
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	public void printmyerror() {
		switch (this.errorno)
		{
		case 1: System.out.println("ERROR 1 - Text file not found!");
			break;
		case 2: System.out.println("ERROR 2 - Cannot create .dat file!");
			break;
		case 3: System.out.println("ERROR 3 - Cannot read .dat file!");
			break;
		case 4: System.out.println("ERROR 4 - Such Option Set does not exist!");
			break;
		case 5: System.out.println("ERROR 5 - Such Option does not exist!");
			break;
		}
	}
	
	// to fix the error, prompt for the right filename
	public String fixError1()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter text file name:");
		String filename = input.next();
		return filename;
	}
}
