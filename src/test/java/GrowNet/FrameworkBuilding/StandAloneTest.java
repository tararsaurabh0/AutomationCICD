package GrowNet.FrameworkBuilding;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GrowNet.FrameworkBuilding.PageObjects.CartPage;
import GrowNet.FrameworkBuilding.PageObjects.CheckoutPage;
import GrowNet.FrameworkBuilding.PageObjects.ConfirmationPage;
import GrowNet.FrameworkBuilding.PageObjects.LandingPage;
import GrowNet.FrameworkBuilding.PageObjects.OrderPage;
import GrowNet.FrameworkBuilding.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;

public class StandAloneTest extends BaseTest{
	// String productName = "IPHONE 13 PRO";
@Test(dataProvider = "getData", groups = {"Purchase"})
public void submitOrder( HashMap<String, String> input ) throws IOException { 
		
		System.out.println("Starting Test ");
		System.out.println(input.get("email") + " || " + input.get("password") + " || " + input.get("productName"));
		// LandingPage landingPage = launchApplication(); this method is in before method annotation now
		landingPage.loginApplication(input.get("email"), input.get("password"));
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		productCatalogue.goToCartPage();
				
		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyProductDisplayed(input.get("productName"));
		AssertJUnit.assertTrue(match);
		
	    cartPage.goToCheckout();
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.selectCountry("India");
	    checkoutPage.clickSubmit();
	    
	    
	    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
	    String confirmationMessage = confirmationPage.getConfirmationMessage();
	    System.out.println("Printing Confirmation Message " + confirmationMessage);
	    //How to ignore case in below statement
	    
	    AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	    System.out.println("Test Passed" + " End OF Test");
		
		}


	//to verify ZARA COAT 3 is displaying in orders page

	@Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
	public void orderHistoryTest(String email, String password, String productName) {
		
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("saurabhtester@gmail.com", "Saurabh@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		boolean match1 =  orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match1);
		
		
	}
	
	@DataProvider
	public Object getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\GrowNet\\FrameworkBuilding\\Data\\purchaseOrder.json");
		System.out.println("getJsonDataToHashMap method Executed succesfully"); 
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}
	
	
}