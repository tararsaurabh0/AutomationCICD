package GrowNet.FrameworkBuilding.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GrowNet.AbstractComponents.AbstractsComponents;

public class OrderPage extends AbstractsComponents
{
	WebDriver driver;
	//Constructor 
	public OrderPage(WebDriver driver) {
		
		super(driver);	
		// initializing driver for usage inside the class 
		this.driver = driver;
		
		// Syntax to initialize PageFactory Elements 
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(css= ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css= "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	

	public boolean verifyOrderDisplay(String productName) {
		boolean match = productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
}
