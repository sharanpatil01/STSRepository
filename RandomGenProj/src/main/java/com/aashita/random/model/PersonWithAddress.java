package com.aashita.random.model;


public class PersonWithAddress {

	public PersonWithAddress(){
		
	}

	public PersonWithAddress(String fn, String ln){
		this.fName = fn;
		this.lName = ln;
	}
	

	public PersonWithAddress(String fn, String ln, String city, String st){
		this.fName = fn;
		this.lName = ln;
		this.city  = city;
		this.state = st;
	}
	
	StringBuilder sb = new StringBuilder();
	
	@Override
	public String toString() {
	
		sb = new StringBuilder().append("Person [");
				
		if (!id.isEmpty()) sb.append(id).append(", ");
		if (!fName.isEmpty()) sb.append(fName).append(", ");
		if (!lName.isEmpty()) sb.append(lName).append(", ");
		if (!city.isEmpty()) sb.append( city ).append( ", " );
		if (!state.isEmpty()) sb.append( state );
				
		sb.append( "]");
		String str = sb.toString().replaceAll(", ]", "]");
		
		return str;
	}
	
	
	private String id = "";
	private String fName = "";
	private String lName = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	
	
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
