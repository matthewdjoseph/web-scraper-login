package com.webscraper.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webscraper.dao.StocksDAO;
import com.webscraper.entity.Stock;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StocksDAO stockDAO;

	@Override
	@Transactional
	public List<Stock> getStocks() {

		return stockDAO.getStocks();
	}

}
