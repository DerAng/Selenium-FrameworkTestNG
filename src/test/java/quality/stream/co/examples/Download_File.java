package quality.stream.co.examples;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;

public class Download_File {
	
	private WebDriver driver;
	private String downloadFilePath="C:\\Users\\derly\\OneDrive\\Escritorio\\Test";
	
	
	@BeforeClass
	  public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		HashMap<String, Object> chromePrefs =  new HashMap<String, Object>();
		
		// Deshabilitar la opción que habre la ventana para la descarga 
		
		chromePrefs.put("profile.default_content_settings.popups", 0);
		
		chromePrefs.put("download.default_directory", downloadFilePath);
		
		// Setiar las preferencias del navegador de forma que podamos 
	    // descargar el fichero directamente.
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(options);
		
	  }
	
	
  @Test
  public void downloadFile() throws InterruptedException {
	  
	  driver.get("https://the-internet.herokuapp.com/download");
	  driver.findElement(By.xpath("//a[@href='download/some-file.txt']")).click();
	  Thread.sleep(2000);
	  File folder = new File(downloadFilePath);
	  
	  // Guardando todos los archivos que se encuentren en la carpeta creada en este arreglo
	  // Se hace para saber si la carpeta esta vacia o si realmente se guardo el archivo descargado
	  
	  File[] listofFiles= folder.listFiles();
	  
	  assertTrue(listofFiles.length>0, "File not download correctly");
	  
  }
  

  @AfterClass
  public void afterClass() {
	  
	 driver.quit();
  }

}
