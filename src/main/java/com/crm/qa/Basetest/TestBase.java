package com.crm.qa.Basetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	
	public static WebDriver driver;
	public static Properties prop;
	

	
	public TestBase()
	{
		try {
			prop= new Properties();
			FileInputStream ip= new FileInputStream("/Users/Ronak/Desktop/Selenium/FreeCrmProject/src/main/java"
					+ "/com/crm/qa/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		public static void initialization() throws MalformedURLException
		{
			ChromeOptions co=new ChromeOptions();
			co.addArguments("headless");
			co.addArguments("--disable-dev-shm-usage");
			String browsername= prop.getProperty("browser");
			
			if(browsername.contentEquals("chrome")){
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver(co);	
				
				//DesiredCapabilities cap=new DesiredCapabilities();
				//cap.setBrowserName(browsername);

				//driver =new RemoteWebDriver(new URL("http://ec2-54-164-215-226.compute-1.amazonaws.com:4444"),cap);
			
	}
			/*else if (browsername.contentEquals("Firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();	
			}
			if (browsername.contentEquals("safari"));

			{
			
				driver=new SafariDriver();

			}	
	*/
			
		
			
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
				driver.get(prop.getProperty("url"));
				
		}
}
