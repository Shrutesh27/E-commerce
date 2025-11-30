package rahulshettyacadamy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import rahulshettyacadamy.abstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindBy (css=".hero-primary")
	WebElement  Thanks;
	

	public String SubmitOrderPage()
	{
		jsUp();
		return Thanks.getText();
		
	}
	

	
	
}
