package com.aashita.ws.contacts;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService  
@SOAPBinding(style = Style.DOCUMENT)  
public interface NSEStocks{  
    @WebMethod String getHelloWorldAsString(String name);  
    
    @WebMethod List<Ohlc5> getOhlcForSymbol(String symbol);
    
    @WebMethod Ohlc5 getLastPriceForStock(String stockname);
    
    @WebMethod List<Ohlc5> getPriceForAllStock();
    
    @WebMethod String addOhlc(Ohlc5 ohlc) throws NSEException;
}  