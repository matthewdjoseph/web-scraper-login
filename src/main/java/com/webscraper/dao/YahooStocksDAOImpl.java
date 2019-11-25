package com.webscraper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.webscraper.entity.YahooStock;

@Repository
public class YahooStocksDAOImpl implements YahooStocksDAO {
	
	@Override
	public List<YahooStock> getStocks() {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(YahooStock.class)
				.buildSessionFactory();
		
		Session currentSession = factory.getCurrentSession();
		currentSession.beginTransaction();
		
		Query<YahooStock> theQuery = currentSession.createQuery("from YahooStock", YahooStock.class);
		
		List<YahooStock> stocks = theQuery.getResultList();
		
		currentSession.close();
		
		return stocks;
	}

}
