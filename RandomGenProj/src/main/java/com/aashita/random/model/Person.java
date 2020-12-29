package com.aashita.random.model;


public class Person {

	public Person(){
		
	}
	
	public Person(String fn, String ln, String city, String st){
		this.fName = fn;
		this.lName = ln;
		this.city  = city;
		this.state = st;
	}
	
	StringBuilder sb = new StringBuilder();
	
	@Override
	public String toString() {
	
		sb = new StringBuilder().append("Person [").append(id).append(", ")
						.append(fName).append(",").append(lName).append(", ").append( city ).append( ", " ).append( state ).append( "]");
		return sb.toString();
	}
	
	
	private String id;
	private String fName;
	private String lName;
	private String address = "";
	private String city;
	private String state;
	private String zipcode;
	
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address == null){
			address = "Street 5, Krishna Devaraya colony";
		}
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
	
}
