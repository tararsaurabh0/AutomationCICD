package GrowNet.FrameworkBuilding.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GrowNet.AbstractComponents.AbstractsComponents;

public class LandingPage extends AbstractsComponents
{
	WebDriver driver;
	//Constructor 
	public LandingPage(WebDriver driver) {
		
		super(driver);	
		// initializing driver for usage inside the class 
		this.driver = driver;
		
		// Syntax to initialize PageFactory Elements 
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) 
	{
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() 
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	
	
}
