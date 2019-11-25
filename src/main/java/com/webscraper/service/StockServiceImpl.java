package com.webscraper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webscraper.dao.YahooStocksDAO;
import com.webscraper.entity.YahooStock;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private YahooStocksDAO stockDAO;

	@Override
	@Transactional
	public List<YahooStock> getStocks() {

		return stockDAO.getStocks();
	}

}
