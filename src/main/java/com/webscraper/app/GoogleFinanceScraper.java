package com.webscraper.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
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
		
		System.out.println("Reached destination...time to scrape!");
		
		List<HtmlElement> stocks = page1.getByXPath("//dev[class='ML43Jb fw-wli bm7Wje HXsnuf']");

		//webClient.close();
	}

}
