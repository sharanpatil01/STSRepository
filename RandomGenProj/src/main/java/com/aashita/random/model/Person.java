package com.aashita.random.model;


public class Person {

	public Person(){
		
	}

	public Person(String fn, String ln){
		this.fName = fn;
		this.lName = ln;
	}
	

	public Person(String id, String fn, String ln){
		this.id = id;
		this.fName = fn;
		this.lName = ln;
	}
	
	StringBuilder sb = new StringBuilder();
	
	@Override
	public String toString() {
	
		sb = new StringBuilder().append("Person [");
				
		if (!id.isEmpty()) sb.append(id).append(", ");
		if (!fName.isEmpty()) sb.append(fName).append(", ");
		if (!lName.isEmpty()) sb.append(lName);
				
		sb.append( "]");
		String str = sb.toString().replaceAll(", ]", "]");
		
		return str;
	}
	
	
	private String id = "";
	private String fName = "";
	private String lName = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
 	
	
}
