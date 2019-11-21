package com.webscraper.app;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GoogleFinanceScraper {

	public static void scrapeGoogle()
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);

		HtmlPage page1 = webClient.getPage("http://www.google.com/finance");
		System.out.println("website reached: " + page1.getTitleText());

		webClient.waitForBackgroundJavaScript(5000);
		
		HtmlAnchor link = page1.getAnchorByText("Your Stocks");
		
		link.click();
		
		webClient.waitForBackgroundJavaScript(5000);
		
		System.out.println("Reached destination...time to scrape! ");
		
		Document stockPage = Jsoup.parse(page1.asText());
		
		Elements stocks = stockPage.select("div.ML43Jb CPqeke");
		
		for(int i=0; i< stocks.size(); i++) {
			System.out.println(stocks.get(i).outerHtml().toString());
		}
		
		System.out.println(stocks.text());

		//webClient.close();
	}

}
