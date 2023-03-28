package com.crm.qa.Testcases;

import java.net.MalformedURLException;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.Basetest.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


public class LoginPageTest extends TestBase{
	LoginPage login;
	HomePage home;
	public LoginPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		initialization();
		login = new LoginPage();
	}
	
	@Test(priority=1)
	public void validatetitletest() throws InterruptedException {
	String title=login.validateTitle();
	AssertJUnit.assertEquals(title,"Free CRM - CRM software for customer relationship management, sales, and support.");
	Thread.sleep(3000);
	}
	
	@Test(priority=2)
	public void validateLogoTest() throws InterruptedException {
		boolean logo= login.validateLogo();
		//Assert.assertEquals(logo, true);
		AssertJUnit.assertTrue(logo);
		Thread.sleep(3000);
	}
	// home is reference object variable of the object homepage.
	@Test(priority=3)
	public void loginTest() throws InterruptedException {
	home=login.performlogin(prop.getProperty("username"), prop.getProperty("password"));
	
	AssertJUnit.assertTrue(driver.getPageSource().contains("CRMPRO"));// verify anytext on lending page
	System.out.println("login Successfull");
	Thread.sleep(2000);
	}
	
	
	@AfterMethod
	public void quit() {
		driver.close();
	}
	
	
}
