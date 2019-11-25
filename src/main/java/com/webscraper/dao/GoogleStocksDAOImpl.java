package com.webscraper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.webscraper.entity.GoogleStock;
import com.webscraper.entity.YahooStock;

@Repository
public class GoogleStocksDAOImpl implements GoogleStocksDAO {
	
	@Override
	public List<GoogleStock> getStocks() {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(YahooStock.class)
				.buildSessionFactory();
		
		Session currentSession = factory.getCurrentSession();
		currentSession.beginTransaction();
		
		Query<GoogleStock> theQuery = currentSession.createQuery("from GoogleStock", GoogleStock.class);
		
		List<GoogleStock> stocks = theQuery.getResultList();
		
		currentSession.close();
		
		return stocks;
	}

}
