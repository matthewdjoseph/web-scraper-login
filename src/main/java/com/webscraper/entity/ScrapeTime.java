package com.webscraper.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ScrapeTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int scrape_time_id;
	
	@Column(name = "scrape_time")
	protected Date scrape_time;
	
	public Date getScrape_time() {
		return scrape_time;
	}

	public void setScrape_time(Date scrape_time) {
		this.scrape_time = scrape_time;
	}
	
	public ScrapeTime() {
		
	}
	
	public ScrapeTime(Date scrape_time) {
		this.scrape_time = scrape_time;
	}

}
