package GrowNet.FrameworkBuilding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import GrowNet.FrameworkBuilding.PageObjects.CartPage;
import GrowNet.FrameworkBuilding.PageObjects.CheckoutPage;
import GrowNet.FrameworkBuilding.PageObjects.ConfirmationPage;
import GrowNet.FrameworkBuilding.PageObjects.LandingPage;
import GrowNet.FrameworkBuilding.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class BaseTest{
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		// Properties class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//GlobalData.properties");
		prop.load(fis);
		String broswerName = prop.getProperty("browser");
		System.setProperty("http.proxyHost", "genproxy.amdocs.com");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("https.proxyHost", "genproxy.amdocs.com");
		System.setProperty("https.proxyPort", "8080");
	

		
		if(broswerName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors","--start-maximized","--auto-open-devtools-for-tabs");		
			driver = new ChromeDriver(options);		
			
		}
		else if (broswerName.equalsIgnoreCase("firefox")) {
            // code for firefox
		}
		else if (broswerName.equalsIgnoreCase("IE")){
			// code for IE
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(groups = "Purchase")
	public LandingPage launchApplication() throws IOException {
		System.out.println("Before Method Annotation Triggered");
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;		
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("After Method Annotation Triggered");
		driver.quit();
	}
	
public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException {
		
		// read json to String - File library supported by java // Converting json to String 
		
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);

		
		
		// String to HashMap need external library --> Jackson Databind 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}

public String getScreenshot(String testCaseName) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName +".png");
	FileUtils.copyFile(source, file);
	
	
	return System.getProperty("user.dir") + "//reports//" + testCaseName +".png";
	}

	

	
	
}