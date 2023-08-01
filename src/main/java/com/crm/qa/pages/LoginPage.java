package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.Basetest.TestBase;

public class LoginPage extends TestBase{

	//Page factory- object repository
	//defining the page libraries .
	//creating the webelement
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(xpath="//a[@contains(text(),'Sign Up')]")
	WebElement signup;
	
	@FindBy(xpath="/html/body/div[2]/div/div[1]/a/img")
	WebElement crmlogo;
	
	// initialize the elements of the page with the help of page factory.
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
// different action on LoginPage	
	public String validateTitle() {
		
		return driver.getTitle();
	}
	public boolean validateLogo() {
		return crmlogo.isDisplayed();
	}
	public HomePage performlogin(String un, String pwd) {
		
		
			username.sendKeys(un);
			password.sendKeys(pwd);
			loginbtn.click();
			return new HomePage();
		}
	}
	
	
	
	
