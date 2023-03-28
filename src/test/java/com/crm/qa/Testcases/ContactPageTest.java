package com.crm.qa.Testcases;


import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.Basetest.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase{
	
	LoginPage login;
	HomePage home;
	ContactPage contact;
	TestUtil testutil;
	
	
	@BeforeMethod
	public void SetUp() throws MalformedURLException {
		initialization();
		testutil=new TestUtil();
		login=new LoginPage();
		contact=new ContactPage();
		home=login.performlogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	

	@DataProvider(name="data")
	public Object[][] NewContactdata(){
		
		Object data[][]=TestUtil.getTestData("Sheet 1", "/Users/Ronak/Desktop/Selenium/FreeCrmProject/src/main/"
				+ "java/com/crm/qa/testdata/TestData.xlsx");
		return data;
	

	}
	
	@Test(priority=2,dataProvider="data")
	public void CreateNewContact(String firstname, String lastname, String emailed) throws InterruptedException {
		testutil.switchToFrame();
		home.navigatetonewcontact();
		contact.EnterNewContact(firstname, lastname, emailed);
		Thread.sleep(3000);
		
	}
	
	
	@AfterMethod
	public void CleanUp() {
		driver.quit();	
	}
	
}
