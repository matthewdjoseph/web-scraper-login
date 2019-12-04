package com.webscraper.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.webscraper.entity.ScrapeTime;

@MappedSuperclass
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int stock_id;

	@Column(name = "symbol")
	protected String symbol;

	@Column(name = "last_price")
	protected String latestPrice;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="scrape_time_id")
	private ScrapeTime scrapeTime;
	
	public ScrapeTime getScrapeTime() {
		return scrapeTime;
	}

	public void setScrapeTime(ScrapeTime scrapeTime) {
		this.scrapeTime = scrapeTime;
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
