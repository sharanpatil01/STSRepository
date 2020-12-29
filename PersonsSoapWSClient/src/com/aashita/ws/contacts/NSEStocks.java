package com.aashita.ws.contacts;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService  
@SOAPBinding(style = Style.DOCUMENT)  
public interface NSEStocks{  
    @WebMethod String getHelloWorldAsString(String name);  
}  