package GrowNet.FrameworkBuilding;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
import GrowNet.FrameworkBuilding.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class ErrorValidations extends BaseTest{
	String productName = "ZARA COAT 3";
@Test
public void loginErrorValidation() throws IOException { 
		
		System.out.println("Starting");
		landingPage.loginApplication("saurabhtester@gmail.com", "Saurabh@12");
		Assert.assertEquals("Incorrect email or password ", landingPage.getErrorMessage());
		
		
//		It checks if logIn is working with or not we are passing wrong credentials in above method
		
	}

@Test
public void productErrorValidation() throws IOException { 
	
	System.out.println("Starting");
	// LandingPage landingPage = launchApplication(); this method is in before method annotation now
	landingPage.loginApplication("saurabhtester@gmail.com", "Saurabh@123");
	
	ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addProductToCart(productName);
	productCatalogue.goToCartPage();
			
	CartPage cartPage = new CartPage(driver);
	boolean match = cartPage.verifyProductDisplayed("ZARA COAT 33"); // wrong name added in method
	AssertJUnit.assertFalse(match);
	
//    cartPage.goToCheckout();
//    CheckoutPage checkoutPage = new CheckoutPage(driver);
//    checkoutPage.selectCountry("India");
//    checkoutPage.clickSubmit();
//    
//    
//    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
//    String confirmationMessage = confirmationPage.getConfirmationMessage();
//    System.out.println("Printing Confirmation Message " + confirmationMessage);
//    //How to ignore case in below statement
//    
//    AssertJUnit.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
//    System.out.println("Test Passed" + " End OF Test");
}
}