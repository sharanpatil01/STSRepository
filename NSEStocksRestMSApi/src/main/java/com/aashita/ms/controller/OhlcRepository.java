package com.aashita.ms.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OhlcRepository extends CrudRepository<Ohlc5, Integer> {

	//List<Ohlc5> findBySymbol(String symbol);
	//Optional<Ohlc5> findById(int id);
}
