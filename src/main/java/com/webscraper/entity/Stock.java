package com.webscraper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int stock_id;

	@Column(name = "symbol")
	protected String symbol;

	@Column(name = "last_price")
	protected String latestPrice;
	
	@Column(name = "scrape_time")
	protected Date scrape_time;
	
	public Date getScrape_time() {
		return scrape_time;
	}

	public void setScrape_time(Date scrape_time) {
		this.scrape_time = scrape_time;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public String getLatestPrice() {
		return latestPrice;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
}
