package com.aashita.ws.contacts;


import org.springframework.data.annotation.Id;

public class Ohlc5 {

	@Id
	private Integer id;

	

	private String symbol;
	private float open_price;
	private float high_price;
	private float low_price;
	private float close_price;
	private float prev_close;
	private float last_price;

	
	private int deliv_qty;
	private java.sql.Date tradeDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getOpen_price() {
		return open_price;
	}
	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}
	public float getHigh_price() {
		return high_price;
	}
	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}
	public float getLow_price() {
		return low_price;
	}
	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}
	public float getClose_price() {
		return close_price;
	}
	public void setClose_price(float close_price) {
		this.close_price = close_price;
	}
	public float getPrev_close() {
		return prev_close;
	}
	public void setPrev_close(float prev_close) {
		this.prev_close = prev_close;
	}
	public float getLast_price() {
		return last_price;
	}
	public void setLast_price(float last_price) {
		this.last_price = last_price;
	}
	public int getDeliv_qty() {
		return deliv_qty;
	}
	public void setDeliv_qty(int deliv_qty) {
		this.deliv_qty = deliv_qty;
	}
	public java.sql.Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(java.sql.Date tradeDate) {
		this.tradeDate = tradeDate;
	}

}
