package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Automotive implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _make, _model;
	private double _basePrice;
	private LinkedHashMap<String, OptionSet> _optionSets;
		
	public Automotive(){
		super();
		_optionSets = new LinkedHashMap<String, OptionSet>();
	}
	
	public Automotive(String model) {
		super();
		this._model = model;
		_optionSets = new LinkedHashMap<String, OptionSet>();
	}
	
	public Automotive(double bprice) {
		super();
		this._basePrice = bprice;
		_optionSets = new LinkedHashMap<String, OptionSet>();
	}
	
	public String getMake() {
		return _make;
	}
	
	public void setMake(String make){
		_make = make;
	}
	public String getModel() {
		return _model;
	}

	public void setModel(String model) {
		_model = model;
	}

	public double getBaseprice() {
		return this._basePrice;
	}

	public void setBaseprice(double bprice) {
		this._basePrice = bprice;
	}
	
	public void addOptionSet (String setname, int count){
		OptionSet os = new OptionSet(setname, count);
		_optionSets.put(setname, os);
	}
	
	public LinkedHashMap<String, OptionSet> getLHM(){
		return _optionSets;
	}
	
	public OptionSet getOptionSet(String setName) {
		return _optionSets.get(setName); // used for matchOptSet
	}
	
	public Option getOption(String setName, String optionName){
		return _optionSets.get(setName).getOption(optionName);
	}

	public void setOption(String setName, int index, String optionName, int price) {
		OptionSet g = _optionSets.get(setName);
		g.setOption(index, optionName, price);
	}
	
	public void setOptionChoice(String setName, String optionName){
		_optionSets.get(setName).setName(optionName);
	}
	
	public Iterator<OptionSet> getOptionSetNamesIterator(){
		Collection<OptionSet> c = _optionSets.values();
		return c.iterator();
	}
	
	public String getOptionChoice(String setName){
		return _optionSets.get(setName).getName();
	}
	
	public int getTotalPrice(){
		Collection<OptionSet> c = _optionSets.values();
		Iterator<OptionSet> i = c.iterator();
		while(i.hasNext())
		{
			OptionSet os = i.next();
		}
		
		return 0;
	}
	
	//Add print method and a tostring method that is nicely formatted.
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		//String price = new DecimalFormat("0.00").format(_basePrice);
		return _make + " " + _model;
	}
}