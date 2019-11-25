package com.webscraper.dao;

import java.util.List;

import com.webscraper.entity.YahooStock;

public interface StocksDAO {
	
	public List<YahooStock> getStocks();

}
