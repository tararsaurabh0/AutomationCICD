package GrowNet.FrameworkBuilding.PageObjects;
import GrowNet.AbstractComponents.AbstractsComponents;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import GrowNet.FrameworkBuilding.PageObjects.ProductCatalogue;


public class ConfirmationPage extends AbstractsComponents {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver); // calling constructor of AbstractComponents
		this.driver = driver; // initializing driver for usage inside the class
		PageFactory.initElements(driver, this); // Syntax to initialize PageFactory Elements
		
	}

	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement confirmationMessage;
	
	
	public String getConfirmationMessage() {
		String text_scrapped = confirmationMessage.getText();
		return text_scrapped;
	}
}
