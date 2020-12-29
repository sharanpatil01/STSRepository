package com.aashita.ws.contacts;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "com.aashita.ws.contacts.NSEStocks")  
public class NSEStocksImpl implements NSEStocks {


	@Override
	//Service Implementation  
	public String getHelloWorldAsString(String name) {  
        return "Hello World JAX-WS " + name;  
    }

	@Override
	public List<Ohlc5> getOhlcForSymbol(String stockname) {
		// TODO Auto-generated method stub
		return NSEUtil.getOhlcListForStock(stockname);
	}

	@Override
	public Ohlc5 getLastPriceForStock(String stockname) {
		// TODO Auto-generated method stub
		return NSEUtil.getOhlcPrice(stockname);
	}

	@Override
	public List<Ohlc5> getPriceForAllStock() {
		// TODO Auto-generated method stub
		return NSEUtil.getAllOhlc();
	}

	@Override
	public String addOhlc(Ohlc5 ohlc) throws NSEException {

		if (NSEUtil.getNonNseStocksNameList().contains(ohlc.getSymbol().toUpperCase()) ) {
			throw new NSEException("Only NSE stocks are added!!  ["+ ohlc.getSymbol() +"] is not listed in NSE/BSE!!!!" );
		}
		
		return "Not yet implemented!!!";
	}  

}
