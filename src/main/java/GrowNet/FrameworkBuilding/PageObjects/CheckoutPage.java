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


public class CheckoutPage extends AbstractsComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver); // calling constructor of AbstractComponents
		this.driver = driver; // initializing driver for usage inside the class
		PageFactory.initElements(driver, this); // Syntax to initialize PageFactory Elements
		
	}

	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryTextBox;
	
	@FindBy (xpath= "//section[@class='ta-results list-group ng-star-inserted']")
	WebElement searchResults;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectIndia;
	
	@FindBy (css = ".action__submit")
	WebElement submitButton;
	
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(countryTextBox, countryName).build().perform();
		waitForElementToAppear(By.xpath("//section[@class='ta-results list-group ng-star-inserted']"));
		selectIndia.click();
		
	}
	
	public void clickSubmit() {
		submitButton.click();
	}
}
