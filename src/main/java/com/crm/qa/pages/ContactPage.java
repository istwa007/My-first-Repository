package com.crm.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.Basetest.TestBase;

public class ContactPage extends TestBase{

	
	@FindBy(name="title")
	WebElement nametitle;
	
	@FindBy(id="first_name")
	WebElement ftname;
	
	@FindBy(id="surname")
	WebElement ltname;
	
	@FindBy(id="email")
	WebElement emailelmt;
	
	@FindBy(xpath="//input[@value=\"Save\"]")
	WebElement savebtn;
	
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	public void NameTitle() {
		
		Select ti= new Select(nametitle);
		ti.selectByValue("Mr.");
		
	}
	
	public void EnterNewContact(String fname ,String lname, String emailid) {
		ftname.clear();
		ftname.sendKeys(fname);
		ltname.clear();
		ltname.sendKeys(lname);
		emailelmt.clear();
		emailelmt.sendKeys(emailid);
		savebtn.click();
		
		
	}
}
