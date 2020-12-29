package com.aashita.ws.contacts;

import javax.xml.ws.Endpoint;

public class NSEStocksPublisher {
	//Endpoint publisher  
    public static void main(String[] args) {  
       Endpoint.publish("http://localhost:8088/ws/nse", new NSEStocksImpl());  
       System.out.println("Services are up and running!!! \ntry out!!! >>> [http://localhost:8088/ws/nse?wsdl]");
    }  
}  
