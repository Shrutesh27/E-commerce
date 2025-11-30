package rahulshettyacadamy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacadamy.PageObjects.CartPage;
import rahulshettyacadamy.PageObjects.OrderPage;

public class AbstractComponents  {
	WebDriver driver;
	//created constructor to catch driver from child class
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[class*='flyInOut']")
	public WebElement ErrorMessage;
	
	@FindBy(css="[routerlink*='/myorders']")
	WebElement OrderHeader;
	//By locator passed as which starts from By.xpath, By.css
	//WebElemnet starts with driver.findelemnet/s
	public void waitForElementToAppear(By findBy) {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	//first Wait for:-	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//Second Wait for:- w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	}

	public void WaitForWebElementToAppear(WebElement FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	//above find by but below Element
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		/*there is technical reson that 2 spinner running behind so automation ytake 4sec time to go to checkout
		 * WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));
		 * w.until(ExpectedConditions.invisibilityOf(ele));
		 */
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		OrderHeader.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}
	public JavascriptExecutor jsDown()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return	(JavascriptExecutor) js.executeScript("window.scrollBy(0,1000)");
	}
	
	public JavascriptExecutor jsUp()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return	(JavascriptExecutor) js.executeScript("window.scrollBy(0,0)");
	}
	
	public JavascriptExecutor jsElement(WebElement ele)
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return	(JavascriptExecutor) js.executeScript("arguments[0].click();", ele);
	}
	
	public JavascriptExecutor jsElementSelect(WebElement ele)
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		return	(JavascriptExecutor) js.executeScript("arguments[0].(true);", ele);
	}
	
	
	
	
	

}
