package com.webscraper.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webscraper.entity.ScrapeTime;
import com.webscraper.entity.YahooScrapeTime;
import com.webscraper.entity.YahooStock;

public class YahooFinanceScraper {

	public static void scrapeSite() throws MalformedURLException {

		RemoteWebDriver driver = getDriver();

		driver.get("https://finance.yahoo.com/");

		System.out.println("Website successfully accessed...\n");
		
		try {

			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("uh-signedin")));
			signIn.click();
	
			System.out.println("Entering username...\n");
	
			WebElement usernameTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
			usernameTextbox.clear();
			usernameTextbox.sendKeys("matthewjoseph.student");
	
			WebElement logIn = wait.until(ExpectedConditions.elementToBeClickable(By.name("signin")));
			driver.executeScript("arguments[0].click()", logIn);
	
			System.out.println("Entering password...\n");
	
			WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-passwd")));
	
			password.sendKeys("future88!");
	
			WebElement passwdSignIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-signin")));
	
			driver.executeScript("arguments[0].click()", passwdSignIn);
	
			System.out.println("Login successful! Proceeding to portfolio...\n");
	
			WebElement portfolios = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Portfolio")));
			driver.executeScript("arguments[0].click()", portfolios);
	
			WebElement myPortfolio = driver.findElementByPartialLinkText("Mine");
			driver.executeScript("arguments[0].click()", myPortfolio);
	
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[aria-label='Symbol']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[aria-label='Last Price']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[aria-label='Chg %']")));
		
		}catch( Exception e) {
			System.out.println("System timed out with exception: ");
			e.printStackTrace();
			throw e;
		}

		System.out.println("Portfolio successfully accessed! Beginning data scrape...\n");

		scrapeData(driver);

	}

	private static RemoteWebDriver getDriver() throws MalformedURLException {

		ChromeOptions chromeOptions = new ChromeOptions();
		URL url = new URL("http://192.168.99.101:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, chromeOptions);

		return driver;

	}
	
	private static void scrapeData(RemoteWebDriver driver) {
		
		ArrayList<String> symbolsText = new ArrayList<String>();
		ArrayList<String> pricesText = new ArrayList<String>();
		ArrayList<String> changesText = new ArrayList<String>();

		List<WebElement> symbols = driver.findElementsByCssSelector("td[aria-label='Symbol']");
		List<WebElement> prices = driver.findElementsByCssSelector("td[aria-label='Last Price']");
		List<WebElement> changes = driver.findElementsByCssSelector("td[aria-label='Chg %']");

		for (int i = 0; i < symbols.size(); i++) {
			symbolsText.add(symbols.get(i).getText());
			pricesText.add(prices.get(i).getText());
			changesText.add(changes.get(i).getText());
		}
		
		saveScrape(symbolsText, pricesText, changesText);
	}
	
	private static void saveScrape(ArrayList<String> symbols, ArrayList<String> prices, ArrayList<String> changes) {

		Date tempScrapeTime = new Date();
		ScrapeTime scrapeTime = new ScrapeTime(tempScrapeTime);

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(YahooStock.class)
				.addAnnotatedClass(YahooScrapeTime.class)
				.buildSessionFactory();

		try(Session scrapeSession = factory.getCurrentSession();) {
			
			scrapeSession.beginTransaction();
			
			for (int i = 0; i < symbols.size(); i++) {

				YahooStock stock = new YahooStock(symbols.get(i), prices.get(i), changes.get(i));
				
				stock.setScrapeTime(scrapeTime);

				scrapeSession.save(stock);
				System.out.println(stock.toString());

			}

			scrapeSession.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
