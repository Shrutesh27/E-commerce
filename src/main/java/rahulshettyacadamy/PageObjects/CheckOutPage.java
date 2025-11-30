package rahulshettyacadamy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import rahulshettyacadamy.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	Select s;
	private By payment=By.cssSelector(".payment");
	

	@FindBy (css="input[placeholder*='Country']")
	private WebElement SendCountry;
	
	@FindBy(css=".list-group-item span")
			List<WebElement> Countries;
	private By countryList=By.cssSelector(".list-group-item");
	
	@FindBy (xpath="//Select[@class='input ddl'][1]")
	private WebElement Month;
	@FindBy (xpath = "//Select[@class='input ddl'][2]")
	private WebElement Date;

	@FindBy (xpath="//a[contains(@class,'action__submit')]")
	private WebElement PlaceOrderBTN;
	
	public void SelectCountry(String Country)

	{
		waitForElementToAppear(payment);
		jsDown();
		SendCountry.sendKeys(Country);
		waitForElementToAppear(countryList);
		Countries.stream().filter(s -> s.getText().toLowerCase().contains(Country.toLowerCase()))
		.findFirst()
		.ifPresent(s -> s.click());
		selectCardDetail();
	}
	
	public void selectCardDetail()
	{
		s= new Select(Month); 
		s.selectByVisibleText("03");
		s= new Select(Date); 
		s.selectByVisibleText("27");
	}
	
	public ConfirmationPage PlaceOrder()
	{
		try {
			PlaceOrderBTN.click();
			System.out.println("Click through Automation");

		} catch (Exception e) {
			jsElement(PlaceOrderBTN);
			System.out.println("Successfully clicked on Button using JavaScript!");
		}
		
		ConfirmationPage submitOrder=new ConfirmationPage(driver);
		return submitOrder;
		
	}
	

	
	
	
}
