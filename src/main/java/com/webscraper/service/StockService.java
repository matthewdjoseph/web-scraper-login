package com.webscraper.service;

import java.util.List;

import com.webscraper.entity.YahooStock;

public interface StockService {
	
	public List<YahooStock> getStocks();

}
