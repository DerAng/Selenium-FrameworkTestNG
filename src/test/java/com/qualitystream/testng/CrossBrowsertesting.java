package com.qualitystream.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;



import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class CrossBrowsertesting {
	
	 WebDriver driver;
	 By searchBox = By.name("q");
	 By videoLocator = By.xpath("//a[@href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");

	
	
	@BeforeClass
	public void beforeClass() {
		
		
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.google.com/");
		
	}
	

	@Test
	public void googleSearch() throws InterruptedException {
		
		WebElement searchbox = driver.findElement(searchBox);
		searchbox.clear();
		searchbox.sendKeys("quality-stream Introducción a la Automatización de pruebas de software");
	    searchbox.submit();
	   
	    WebDriverWait wait = new WebDriverWait(driver,7);
	    wait.until(ExpectedConditions.presenceOfElementLocated(videoLocator));
	    
	    assertTrue(driver.findElement(videoLocator).isDisplayed());
	   
	    
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
