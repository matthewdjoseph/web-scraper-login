package com.webscraper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class YahooStock extends Stock{

	@Column(name = "change_percentage")
	private String change;

	public YahooStock() {

	}

	public YahooStock(String symbol, String latestPrice, String change) {
		this.symbol = symbol;
		this.latestPrice = latestPrice;
		this.change = change;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	@Override
	public String toString() {
		return "Stock{" + "stock_id='" + stock_id + '\'' + ", symbol='" + symbol + '\'' + ", latestPrice='"
				+ latestPrice + '\'' + ", change='" + change + '\'' + '}';
	}
}
