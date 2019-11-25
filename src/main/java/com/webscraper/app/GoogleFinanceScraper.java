package com.webscraper.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.webscraper.entity.GoogleStock;

public class GoogleFinanceScraper {

	public static void scrapeGoogle()
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		try (WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60);) {

			HtmlPage page1 = webClient.getPage(
					"https://www.google.com/finance#wptab=s:H4sIAAAAAAAAAOPQeMSozC3w8sc9YSmpSWtOXmMU4RJyy8xLzEtO9UnMS8nMSw9ITE_l2cXEHekfGhQfHOLv7B28iJU9DaIGAAUYQO1AAAAA");

			System.out.println("website reached: " + page1.getTitleText());
			
			page1.wait(5000);
			
			System.out.println("Time to scrape! ");

			Document stockPage = Jsoup.parse(page1.asXml());

			Elements stockSymbols = stockPage.getElementsByClass("j7FfMb");
			Elements stockPrices = stockPage.getElementsByClass("IsqQVc iqkv69px7uo4-zJFzKq8ukm8");
			Elements stockChange = stockPage.getElementsByClass("iMowtE7YxIR4-CCT9_i-ytLA");

			System.out.println("Elements scraped, preparing for database entry...");

			Date scrapeTime = new Date();

			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(GoogleStock.class).buildSessionFactory();

			try(Session scrapeSession = factory.getCurrentSession();) {
				
				scrapeSession.beginTransaction();

				for (int i = 0; i < stockSymbols.size(); i++) {
					GoogleStock stock = new GoogleStock(stockSymbols.get(i).text(), stockPrices.get(i).text(),
							stockChange.get(i).text(), scrapeTime);

					scrapeSession.save(stock);
					System.out.println(stock.toString());
				}

				scrapeSession.getTransaction().commit();

			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
