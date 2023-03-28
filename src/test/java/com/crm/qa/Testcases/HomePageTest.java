package com.crm.qa.Testcases;

import java.net.MalformedURLException;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.Basetest.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	LoginPage login;
	HomePage home;
	TestUtil testutil;
	public HomePageTest() {
		
		super();
		
	}
	


	@BeforeMethod
	public void setUp() throws MalformedURLException {
	initialization();
	testutil= new TestUtil();
	login=new LoginPage();
	home=login.performlogin(prop.getProperty("username"),prop.getProperty("password"));
}
	
	@Test
	public void label() {
		testutil.switchToFrame();
		boolean label=home.usernamelabel();
		AssertJUnit.assertTrue(label);
		
		
	}
	
	
	@Test
	public void newcontact() throws InterruptedException {
		testutil.switchToFrame();
		home.navigatetonewcontact();
		Thread.sleep(3000);
		AssertJUnit.assertTrue(driver.getPageSource().contains("Contact Information"));
		System.out.println("enter new contact details");
		
	}
	
	@AfterMethod
	
	public void cleanup() {
		driver.quit();
	}

}
