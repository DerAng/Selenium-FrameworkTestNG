package com.reportsExample.qs;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class SearchBlouses {
  
	
	WebDriver driver;
	By searchBoxLocator = By.id("search_query_top");
	By resultsLocator = By.cssSelector("span.heading-counter");
	
	
  @BeforeClass
  public void beforeClass() {
	  
	    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://automationpractice.com/index.php"); 
	  
	  
  }
  
  @Test
  public void search_Blouses() {
	  
	  WebElement searchbox= driver.findElement(searchBoxLocator);
	  searchbox.clear();
	  searchbox.sendKeys("blouse");
	  searchbox.submit();
	  
	  WebDriverWait wait= new WebDriverWait(driver,7);
	  wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));
	  
	  System.out.println("This is the result number" + driver.findElement(resultsLocator).getText());
	  assertEquals(driver.findElement(resultsLocator).getText(), "1 result has been found.");
	  
  }
  

  @AfterClass
  public void afterClass() {
	  // cerrar el navegador
	  
	  driver.close();
  }


}
