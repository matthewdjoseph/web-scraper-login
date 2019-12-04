package com.webscraper.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "scrape_time")
public class YahooScrapeTime extends ScrapeTime {

	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="scrape_time",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<YahooStock> stocks;
	
	public List<YahooStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<YahooStock> stocks) {
		this.stocks = stocks;
	}
	
	public void add(YahooStock stock) {
		if(stocks == null) {
			stocks = new ArrayList<>();
		}
		
		stocks.add(stock);
		stock.setScrapeTime(this);
	}
}
