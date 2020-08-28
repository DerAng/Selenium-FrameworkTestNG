package quality.stream.co.examples;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckDownloadedFile {
	
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/download");
		
	}

	@Test
	public void  checkDownloadedFile() throws MalformedURLException, IOException {
		
      String link= driver.findElement(By.xpath("//a[@href='download/test.txt']")).getAttribute("href");
      //download/some-file.txt
      HttpURLConnection httpConnection = (HttpURLConnection) (new URL(link).openConnection());
      httpConnection.setRequestMethod("HEAD");
      httpConnection.connect();
      
      String contentType =  httpConnection.getContentType();
      int contentLenght = httpConnection.getContentLength();
      
      System.out.println("Cotent Type: "+contentType);
      System.out.println("Content Lenght:" +contentLenght);
      
      assertEquals(contentType,"application/octet-stream");
      
      assertNotEquals(contentLenght, 0);
     
	}
	
	

	@AfterClass
	public void afterClass() {
		
		driver.quit();
	}

}
