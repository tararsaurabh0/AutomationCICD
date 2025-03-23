package GrowNet.FrameworkBuilding.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GrowNet.AbstractComponents.AbstractsComponents;

public class ProductCatalogue extends AbstractsComponents
{
	WebDriver driver;
	//Constructor 
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		
		// initializing driver for usage inside the class 
		this.driver = driver;
		
		// Syntax to initialize PageFactory Elements 
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addProductToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addProductToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
    }
	}


