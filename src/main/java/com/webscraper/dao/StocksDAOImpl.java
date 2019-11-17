package com.webscraper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.webscraper.entity.Stock;

@Repository
public class StocksDAOImpl implements StocksDAO {
	
	@Override
	public List<Stock> getStocks() {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Stock.class)
				.buildSessionFactory();
		
		Session currentSession = factory.getCurrentSession();
		currentSession.beginTransaction();
		
		Query<Stock> theQuery = currentSession.createQuery("from Stock", Stock.class);
		
		List<Stock> stocks = theQuery.getResultList();
		
		currentSession.close();
		
		return stocks;
	}

}
