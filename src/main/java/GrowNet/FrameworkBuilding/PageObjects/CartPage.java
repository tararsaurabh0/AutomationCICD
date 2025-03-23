package GrowNet.FrameworkBuilding.PageObjects;
import GrowNet.AbstractComponents.AbstractsComponents;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import GrowNet.FrameworkBuilding.PageObjects.ProductCatalogue;

public class CartPage extends AbstractsComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver); // calling constructor of AbstractComponents
		this.driver = driver; // initializing driver for usage inside the class
		PageFactory.initElements(driver, this); // Syntax to initialize PageFactory Elements
		
	}

	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	public boolean verifyProductDisplayed(String productName)
	{
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));		
		return match;
	}
	
	public void goToCheckout() {
		checkoutButton.click();
		
		
	}

	
}
