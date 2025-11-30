package rahulshettyacadamy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacadamy.abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);//use super to pass driver to parent
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	By productsBy=By.cssSelector(".mb-3");
	By addTocart=By.cssSelector("button[class='btn w-10 rounded']");
	By Toast=By.cssSelector("#toast-container");// By locator
	@FindBy (css=".ng-animating")
	WebElement Spinner;//used coz driver.findelemnet
	public List<WebElement> getProductList()
	{
		//in this function wait until it visible then return products
		waitForElementToAppear(productsBy);
	//we cannot pass pageFactory as in abstract we passed By locator so productsBy created
		return products;//return list of products
	}
	public WebElement getProductName(String ProductName)
	{
		WebElement prod= getProductList().stream() .filter(s ->
		 s.findElement(By.cssSelector("b")).getText().contains(ProductName)).findFirst() 
				.orElse(null);
		return prod;
	}
	
	//for below we pass product using getProductname then first that will run 
	//then actual method will run
	/*
	 * public void getProductLists() { getProductList().stream() .map(s ->
	 * s.findElement(By.cssSelector("b")).getText()).findFirst().ifPresent(s->System
	 * .out.println(s));
	 * 
	 * }
	 */
	
	public String getNameProdcatalogue(String productName)
	{
		WebElement prod=getProductName(productName);
		String Prodname=prod.findElement(By.cssSelector("b")).getText();
		return Prodname;
	}
	public String getPrice(String productName)
	{
		WebElement prod=getProductName(productName);
		String PriceofProd= prod.findElement(By.cssSelector(".card-body .text-muted")).getText();
		return PriceofProd;
	}
	
	public void AddProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductName(productName);
		prod.findElement(addTocart).click();
		waitForElementToAppear(Toast);
		waitForElementToDisAppear(Spinner);
	}

}
