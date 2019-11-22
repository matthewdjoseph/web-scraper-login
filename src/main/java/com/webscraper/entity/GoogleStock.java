package com.webscraper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "googlestocks")
public class GoogleStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stock_id;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "last_price")
	private String latestPrice;

	@Column(name = "change_points")
	private String change;
	
	@Column(name = "scrape_time")
	private Date scrape_time;

	public Date getScrape_time() {
		return scrape_time;
	}

	public void setScrape_time(Date scrape_time) {
		this.scrape_time = scrape_time;
	}

	public GoogleStock() {

	}

	public GoogleStock(String symbol, String latestPrice, String change, Date scrapeTime) {
		this.symbol = symbol;
		this.latestPrice = latestPrice;
		this.change = change;
		this.scrape_time = scrapeTime;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getLatestPrice() {
		return latestPrice;
	}

	public String getChange() {
		return change;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	@Override
	public String toString() {
		return "Stock{" + "stock_id='" + stock_id + '\'' + ", symbol='" + symbol + '\'' + ", latestPrice='"
				+ latestPrice + '\'' + ", change='" + change + '\'' + '}';
	}
}
