package com.aashita.ws.persons.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.aashita.ws.contacts.NSEStocks;

public class NSEStocksWSClient {

	
	public static void main(String[] args) throws Exception {  
	    URL url = new URL("http://localhost:8088/ws/hello?wsdl");  
	   
	    //1st argument service URI, refer to wsdl document above  
	    //2nd argument is service name, refer to wsdl document above  
	    
	    	QName qname = new QName("http://localhost:8088/", "NSEStocksImplService");  
	        Service service = Service.create(url, qname);  
	        
	        NSEStocks hello = service.getPort(NSEStocks.class);  
	        System.out.println(hello.getHelloWorldAsString("javatpoint document"));  
	     }  
}
