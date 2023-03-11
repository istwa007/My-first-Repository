package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.Basetest.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: swati agarwal')]")
	WebElement usernameLabel;
	
	@FindBy(xpath=("//a[contains(text(),'Contacts')]"))
	WebElement contactbtn;
	
	@FindBy(xpath="//a[text()='New Contact']")
		WebElement newcontactbtn;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean usernamelabel() {
		return usernameLabel.isDisplayed();
	}
	
	public ContactPage navigatetonewcontact() 
	{
		
		Actions action= new Actions(driver);
		action.moveToElement(contactbtn);
		action.moveToElement(newcontactbtn).click().build().perform();
		
		return new ContactPage();
			}
			

	
	
	
}
