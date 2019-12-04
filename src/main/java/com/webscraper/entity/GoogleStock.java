package com.webscraper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "googlestocks")
public class GoogleStock extends Stock{

	@Column(name = "change_points")
	private String change;
	
	public String getChange() {
		return change;
	}
	
	public void setChange(String change) {
		this.change = change;
	}

	public GoogleStock() {

	}

	public GoogleStock(String symbol, String latestPrice, String change, Date scrapeTime) {
		this.symbol = symbol;
		this.latestPrice = latestPrice;
		this.change = change;
	}

	@Override
	public String toString() {
		return "Stock{" + "stock_id='" + stock_id + '\'' + ", symbol='" + symbol + '\'' + ", latestPrice='"
				+ latestPrice + '\'' + ", change='" + change + '\'' + '}';
	}
}
