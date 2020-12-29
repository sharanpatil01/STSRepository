package com.aashita.ms.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NSEMain {
	  @Autowired
		public OhlcRepository ohlcRepository;
	  
	  long itrSize = 0;

	  @GetMapping(path="/nse/all")
	  public @ResponseBody Iterable<Ohlc5> getAllUsers() {
	    // This returns a JSON or XML with the users
	    Iterable<Ohlc5> itr =		ohlcRepository.findAll();
	    itrSize = ohlcRepository.count();
	    System.out.println("total records returns are : " + itrSize);
	    return itr;
	  }
	  
	  /*
	  @GetMapping(path="/nse/id/{id}")
	  public @ResponseBody Optional<Ohlc5> findById(@RequestParam int id){
		  return ohlcRepository.findById(id);
	  }
	  
	  @GetMapping(path="/nse/symbol/{symbol}")
	  public @ResponseBody Iterable<Ohlc5> findBySymbol(@RequestParam String symbol){
		  return ohlcRepository.findBySymbol(symbol);
	  }
	  */
}
