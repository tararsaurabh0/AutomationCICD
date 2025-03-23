package GrowNet.AbstractComponents;
import GrowNet.FrameworkBuilding.PageObjects.*;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractsComponents
{
	WebDriver driver;
	
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(xpath ="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	//Constructor 
	public AbstractsComponents(WebDriver driver) {
		// initializing driver for usage inside the class 
		this.driver = driver;
		
		// Syntax to initialize PageFactory Elements 
		PageFactory.initElements(driver, this);
	}
    
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void goToCartPage() {
		cartIcon.click();
		
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
}
