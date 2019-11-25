package com.webscraper.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.webscraper.app.GoogleFinanceScraper;
import com.webscraper.app.ScrapeStockSite;
import com.webscraper.entity.YahooStock;
import com.webscraper.service.StockService;

@Controller
public class AppController {

	@Autowired
	private StockService stockService;

	@GetMapping("/")
	public String showHome(Model theModel) {
		List<YahooStock> theStocks = stockService.getStocks();

		theModel.addAttribute("stocks", theStocks);

		return "home";
	}

	@RequestMapping("/scrapeData")
	public String scrapeData(@ModelAttribute("stock") YahooStock stocks) throws MalformedURLException {

		ScrapeStockSite.scrapeSite();

		return "redirect:/";
	}
	
	@RequestMapping("/scrapeGoogle")
	public String scrapeGoogle() throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		
		GoogleFinanceScraper.scrapeGoogle();
		
		return "redirect:/";
	}
	
	@GetMapping("/about")
	public String showAbout() {
		
		return "about";
	}

}
